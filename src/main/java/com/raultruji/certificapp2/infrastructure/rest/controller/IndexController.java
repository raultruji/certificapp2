package com.raultruji.certificapp2.infrastructure.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("\"/certificapp/index\"")
@RequiredArgsConstructor
public class IndexController {

	//inyectamos el objeto para poder acceder a la info de la sess
	@Autowired
	private SessionRegistry sessionRegistry;
	
	
	@GetMapping
	public String getIndexPage() {
		return "index";
	}
	
	@GetMapping("/sessionInfo")
	public ResponseEntity<?> getDetailsSession(){
		
		String sessionId="";
		User userObject = null;
		//jackson serializa el map en un json. Utilizamos json para pasar la info de user y sessId
		Map<String,Object> response = new HashMap<>();
		//get all active sess
		List<Object> sessions = sessionRegistry.getAllPrincipals();
		for(Object sess:sessions) {
			if(sess instanceof User) { 
				//recuperando usuario e info
				User user = (User) sess;
				userObject = user;
			//(usuario del que se recupera sess (boolean incluir expired sess)
			List<SessionInformation> sessInformations= sessionRegistry.getAllSessions(sess, false);
			for(SessionInformation sessInfo : sessInformations) {
				sessionId = sessInfo.getSessionId();			
				}
			}
			
		}
		response.put("response", "yeyy");
		response.put("sessionId", sessionId);
		response.put("sessionuser", userObject);
		
		
		return ResponseEntity.ok(response); //TODO SessionID 
	}
	
}
