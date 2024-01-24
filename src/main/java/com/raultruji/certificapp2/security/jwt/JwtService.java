package com.raultruji.certificapp2.security.jwt;

import java.security.Key;
import java.security.SecureRandom;
import java.sql.Date;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	
	 private static final String SECRET_KEY="586E3272357538782F413F4428472B4B6250655368566B597033733676397924";
	
	public String getToken(UserDetails user) {
		
		return getToken(new HashMap<>(),user);
	}

	//extraClaims el tipo de datos 
	private String getToken(Map<String,Object> extraClaims, UserDetails user) {
		
		return Jwts.builder()
				.claims(extraClaims)
				.subject(user.getUsername())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+1000*60*60*24)) //1 day exp
				//.signWith(getKey(), SignatureAlgorithm.HS256)				
				.signWith(getKey())				
				.compact();
	}

	private Key getKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String getUsernameFromToken(String token) {
		return getClaim(token, Claims::getSubject);
	}

	public <T>T getClaim(String token, Function<Claims,T> claimsResolver) {
			final Claims claims = getAllClaims(token);
			return claimsResolver.apply(claims);
	}

	public boolean isTokenValid(String token, UserDetails userDetails) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private Claims getAllClaims(String token){
		
		 return Jwts
	            .parser()
	            .setSigningKey(getKey())	            
	            .build()
	            .parseSignedClaims(token)
	            .getPayload();
	}

}
