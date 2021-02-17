package com.jwtsampleapp.api.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	@Value("${app.secret}")
	private String secret;
	
	/**
	 *1. Generating Token
	 * 
	 * @param subject
	 * @return
	 */
	public String generateToken(String subject) {
		return Jwts.builder()
				.setSubject(subject)
				.setIssuer("Rakesh Sunkari")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+TimeUnit.MINUTES.toMillis(100)))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
	}
	
	
	/**2. Reading claims or Reading token data
	 * 
	 * @param token
	 * @return
	 */
	public Claims getClaims(String token) {
		return Jwts.parser()
				.setSigningKey(secret.getBytes())
				.parseClaimsJws(token)
				.getBody();
	}
	
	/**
	 * 3. Read Expire Date
	 * 
	 * @param token
	 * @return
	 */
	public Date getExpDate(String token) {
		return getClaims(token).getExpiration();
	}
	
	/**4. Read Subject
	 * 
	 * @param token
	 * @return
	 */
	public String getUsername(String token) {
		return getClaims(token).getSubject();
	}
	
	/**
	 * 5. Validate Exp Date
	 * 
	 * @param token
	 * @return
	 */
	public boolean isTokenExp(String token) {
		Date expDate = getExpDate(token);
		return expDate.before(new Date(System.currentTimeMillis()));
	}
	
	/**validate username in token and database checking token is expired or not
	 * 
	 * @param token
	 * @param username
	 * @return
	 */
	public boolean validateToken(String token, String username) {
		String tokenUsername = getUsername(token);
		return (username.equals(tokenUsername) && !isTokenExp(token));
 	}
}
