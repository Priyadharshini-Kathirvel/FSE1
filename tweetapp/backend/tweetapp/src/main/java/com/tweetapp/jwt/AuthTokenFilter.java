package com.tweetapp.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tweetapp.service.JwtUtilService;
import com.tweetapp.service.UserDetailsServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AuthTokenFilter extends OncePerRequestFilter {

//	@Autowired
	private JwtUtilService jwtUtilService = new JwtUtilService();
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		log.info("START - doFilterInternal() AuthTokenFilter");
		
		String authorizationHeader = request.getHeader("Authorization");
		String token=null;
		String email=null;
		
		if(authorizationHeader !=null && authorizationHeader.startsWith("Bearer")) {
			token = authorizationHeader.substring(7);
			email=jwtUtilService.extractUsername(token);
		}
		
		if(email!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			var userDetails = userDetailsService.loadUserByUsername(email);
			if(Boolean.TRUE.equals(jwtUtilService.validateToken(token, userDetails))) {
				var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		
		filterChain.doFilter(request, response);
		
		log.info("END - doFilterInternal() AuthTokenFilter");
		
	}
	
	
}
