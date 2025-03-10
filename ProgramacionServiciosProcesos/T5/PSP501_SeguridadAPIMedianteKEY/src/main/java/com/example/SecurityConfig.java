package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {

	private final ApiKeyFilter apiKeyFilter;

	public SecurityConfig(ApiKeyFilter apiKeyFilter) {
		this.apiKeyFilter = apiKeyFilter;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeHttpRequests(auth -> auth.requestMatchers("/api/**").permitAll().anyRequest().authenticated())
				.addFilterBefore(apiKeyFilter, BasicAuthenticationFilter.class);

		return http.build();
	}
}
