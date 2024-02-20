package com.raultruji.certificapp2.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.raultruji.certificapp2.repositories.IUserRepository;
import com.raultruji.certificapp2.security.auth.CustomAuthenticationProvider;

import lombok.RequiredArgsConstructor;
//Filtro de seguridad

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	 @Autowired
     private CustomAuthenticationProvider authenticationProvider;
/*
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final AuthenticationProvider authProvider;
*/
	/*
	@Bean
	public SecurityFilterChain SecurityFilterChain(HttpSecurity http)throws Exception{
		return http
				//crossSite Request forgery. Se deshabilita, si no se trabaja con formularios (otra api,...)
			.csrf(csrf ->
					csrf
					.disable())
			.authorizeHttpRequests(authRequest ->
				authRequest
					.requestMatchers("/auth/**").permitAll()
					.anyRequest().authenticated()
					)
			//.formLogin(withDefaults())
			.sessionManagement(sess -> sess
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authenticationProvider(authProvider)
			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
			.build();				
				
	}	
	*/
	//alternative 1
	/*
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity)throws Exception{
		//TODO
		return httpSecurity
				//urls protegidas
				.authorizeHttpRequests(authRequest ->
				authRequest//requestmatchers : url permitidas
					.requestMatchers("/auth/**").permitAll()
					//cualquier otro se tiene que autenticar
					.anyRequest().authenticated()
					)
				//permitir acceso publico al form login
				.formLogin(formLog -> 
				formLog
					.permitAll())
				.build();
	}
	*/
	
	//alternative 2
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity)throws Exception{
		
		return httpSecurity
				.authorizeHttpRequests(auth -> {
					//url permitidas
					auth.requestMatchers("/certificapp/**").permitAll();
					//acceso  a users solo con rol ADMIN
					auth.requestMatchers("/users/**")
					.hasRole("ADMIN");
					//resto
					auth.anyRequest().authenticated();
					
				})
				.formLogin(fl -> {
					//TODO problemas para utilizar custom login
					fl.loginPage("/certificapp/login").permitAll();
					//hidding spring security
					//fl.loginProcessingUrl("/perform_login");
					//para redirigir a una URL tras login/inicio session 
					fl.successHandler(successHandler())
						.permitAll();						
				})			
				// politica de creacion de session
				//ALWAYS, IF_REQUIRED, NEVER, STATELESS:
				//Crea una sess si no hay ninguna. Si hay una la reutiliza
				//Igual pero un poco + estricto con la creacion de sess
				//No crea ninguna, pero si ya existe la reutiliza
				//Todas las solicitudes las hace stateless, sin guardar ningun dato
				.sessionManagement(session -> {
						session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
								.maximumSessions(1)
								.expiredUrl("/certificapp/login") //redirect when sess is expired
								.sessionRegistry(sessionRegistry());
						session.invalidSessionUrl("/certificapp/login"); //redirect if not a correct auth
						session.sessionFixation() //seguridad robo de id session (fixation attack)
								.migrateSession() //si se detecta ataque de fijacion de session 
								//se genera otro id conservando la info de la sess
								//.newSession //estrategia que crea nueva session.						
								;
				})
				//envia user  + pass (auth) en el header de la request (mejor no hacerlo asi)
				//.httpBasic(Customizer.withDefaults())
				//authentication Provider customizado para acceder al name + pass de la bdd
				.authenticationProvider(authenticationProvider)
				.build();
	}
	
	@Bean
	public AuthenticationSuccessHandler successHandler() {
		return ((request, response, autentication)->{
			response.sendRedirect("/certificapp/index");
			//response.sendRedirect("/certificapp/main_menu");
		});

	}
	
	
	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}	
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return  new BCryptPasswordEncoder();
    }
	@Bean
	public UserDetailsService userDetailService(IUserRepository userRepository) {
		return username -> userRepository.findByUsuario(username)
				.orElseThrow(()-> new UsernameNotFoundException("Usuario "+username+" not found"));
	}
	
}
	
