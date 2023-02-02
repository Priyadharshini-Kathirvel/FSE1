package com.tweetapp.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JwtUtilService {
	
	private String secretKey = "secret";

	public String generateToken(String email) {
		log.info("START - generateToken() JwtUtilService");
		Map<String, Object> claims = new HashMap<>();
		log.info("END - generateToken() JwtUtilService");
		return createToken(claims,email);
	}

	private String createToken(Map<String, Object> claims, String email) {
		log.info("START - createToken() JwtUtilService");
		log.info("END - createToken() JwtUtilService");
		return Jwts.builder().setClaims(claims).setSubject(email).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS512, secretKey).compact();
	}

	public String extractUsername(String token) {
		log.info("START - extractUsername() JwtUtilService");
		log.info("END - extractUsername() JwtUtilService");
		return extractClaim(token, Claims :: getSubject);
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		log.info("START - extractClaim() JwtUtilService");
		final var claims = extractAllClaims(token);
		log.info("END - extractClaim() JwtUtilService");
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		log.info("START - extractAllClaims() JwtUtilService");
		log.info("END - extractAllClaims() JwtUtilService");
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	}
	
	private Boolean isTokenExpired(String token) {
		log.info("START - isTokenExpired() JwtUtilService");
		log.info("END - isTokenExpired() JwtUtilService");
		return extractExpiration(token).before(new Date());
	}
	
	private Date extractExpiration(String token) {
		log.info("START - extractExpiration() JwtUtilService");
		log.info("END - extractExpiration() JwtUtilService");
		return extractClaim(token, Claims::getExpiration);
	}
	
	public Boolean validateToken(String token, UserDetails user) {
		log.info("START - validateToken() JwtUtilService");
		log.info("END - validateToken() JwtUtilService");
		final String username = extractUsername(token);
		return username.equals(user.getUsername()) && !isTokenExpired(token);
	}

}
