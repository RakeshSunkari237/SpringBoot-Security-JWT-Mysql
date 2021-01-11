package com.jwtsampleapp.api.auth.service;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jwtsampleapp.api.util.JwtUtil;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;
	
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// 1. Read Token form header
		String token = request.getHeader("Authorization");
		if(token != null ) {
			// do validation
			String username = jwtUtil.getUsername(token);
			
			// username should not be empty and context-auth must be empty
			if(username != null &&  SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails usr = userDetailsService.loadUserByUsername(username);
				
				//validate token
				boolean isValid = jwtUtil.validateToken(token, usr.getUsername());
				
				if(isValid) {
					//create authentication Token
					UsernamePasswordAuthenticationToken authToken
					=new UsernamePasswordAuthenticationToken(username, usr.getPassword(),usr.getAuthorities());
					
					// add auth details to current request
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					
					//final object stored in Security context with user details (un, pwd roles)
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
			}
		}
		filterChain.doFilter(request, response);
		
		
	}

}
