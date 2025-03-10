package com.example;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {

	@Value("${api.key}")
	private String apiKey;

	private static final String API_KEY_HEADER = "X-API-KEY";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String requestApiKey = request.getHeader(API_KEY_HEADER);

		if (requestApiKey == null || !requestApiKey.equals(apiKey)) {
			response.setStatus(HttpStatus.FORBIDDEN.value());
			response.getWriter().write("Error 403. Acceso denegado. La API Key no es correcta.");
			return;
		}

		filterChain.doFilter(request, response);
	}
}
