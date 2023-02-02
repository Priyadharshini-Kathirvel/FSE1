package com.tweetapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.tweetapp.jwt.AuthTokenFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableWebMvc
public class WebSecurityConfig {

	@Autowired
	com.tweetapp.service.UserDetailsServiceImpl userDetailsService;

	private com.tweetapp.jwt.AuthEntryPointJwt unauthorizedHandler;

	@Bean
	public com.tweetapp.jwt.AuthTokenFilter authenticationJwtTokenFilter() {
		log.info("START - authenticationJwtTokenFilter() WebSecurityConfig");
		log.info("END - authenticationJwtTokenFilter() WebSecurityConfig");
		return new AuthTokenFilter();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		log.info("START - authenticationProvider() WebSecurityConfig");
		var authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		log.info("END - authenticationProvider() WebSecurityConfig");
		return authenticationProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		log.info("START - authenticationManager() WebSecurityConfig");
		log.info("END - authenticationManager() WebSecurityConfig");
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		log.info("START - passwordEncoder() WebSecurityConfig");
		log.info("END - passwordEncoder() WebSecurityConfig");
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		log.info("START - filterChain() WebSecurityConfig");
		httpSecurity.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		httpSecurity.authenticationProvider(authenticationProvider());
		httpSecurity.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		httpSecurity.authorizeHttpRequests().requestMatchers("/api/v1.0/tweets/**").permitAll().and().formLogin().disable();
		httpSecurity.cors().and().csrf().disable();
		log.info("END - filterChain() WebSecurityConfig");
		return httpSecurity.build();

	}
}
