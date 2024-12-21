package com.bechir.sante.security;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled=true)

public class SecurityConfig {

//	@Bean
//	public SecurityFilterChain filterChain (HttpSecurity http) throws Exception
//	{
//		http.sessionManagement( session ->
//		session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//		.csrf( csrf -> csrf.disable())
//		.cors(cors -> cors.configurationSource(new CorsConfigurationSource()
//		{
//		 @Override
//		 public CorsConfiguration getCorsConfiguration(HttpServletRequest
//		request) {
//		 CorsConfiguration cors = new CorsConfiguration();
//
//		 cors.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
//		cors.setAllowedMethods(Collections.singletonList("*"));
//		cors.setAllowedHeaders(Collections.singletonList("*"));
//		cors.setExposedHeaders(Collections.singletonList("Authorization"));
//		 return cors;
//		 }
//		 }).and()
//				
//				)
		
		
//		.authorizeHttpRequests();
//		.anyRequest().permitAll();
		

//		 .authorizeHttpRequests( requests -> requests
//				    .requestMatchers("/api/image/**").permitAll() // Permet l'accès public pour les routes d'image
//
//		            .requestMatchers(HttpMethod.GET, "/api/image/getImages/**").hasAnyAuthority("USER", "ADMIN")
//		            .requestMatchers(HttpMethod.GET, "/api/image/loadfromFS/**").hasAnyAuthority("USER", "ADMIN")
//		            .requestMatchers(HttpMethod.GET, "/api/image/getAllImages").hasAnyAuthority("USER", "ADMIN")
//		            .requestMatchers(HttpMethod.POST, "/api/image/upload").hasAuthority("ADMIN")
//		            .requestMatchers(HttpMethod.POST, "/api/image/uploadFS/**").hasAuthority("ADMIN")
//		            .requestMatchers(HttpMethod.DELETE, "/api/image/delete/**").hasAuthority("ADMIN")
//		            
//		            .requestMatchers(HttpMethod.GET, "/api/image/sante/getImagesSante/**").hasAnyAuthority("USER", "ADMIN")
//		            .requestMatchers(HttpMethod.GET, "/api/image/sante/loadfromFSSante/**").hasAnyAuthority("USER", "ADMIN")
//		            .requestMatchers(HttpMethod.GET, "/api/image/sante/getAllImagesSante").hasAnyAuthority("USER", "ADMIN")
//		            .requestMatchers(HttpMethod.POST, "/api/image/sante/uploadSante").hasAuthority("ADMIN")
//		            .requestMatchers(HttpMethod.POST, "/api/image/sante/uploadFSSante/**").hasAuthority("ADMIN")
//		            .requestMatchers(HttpMethod.DELETE, "/api/image/sante/delete/**").hasAuthority("ADMIN")
//		
//		.requestMatchers("/ligue/**").hasAnyAuthority("ADMIN","USER"),
//		.anyRequest().authenticated() ),
//		.addFilterBefore(new JWTAuthorizationFilter(),
//		 UsernamePasswordAuthenticationFilter.class);
//		return http.build();
//		}
//	
	
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration cors = new CorsConfiguration();
	    cors.setAllowedOrigins(Collections.singletonList("http://localhost:4200")); // Autoriser les requêtes depuis Angular
	    cors.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Autoriser toutes les méthodes HTTP
	    cors.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type")); // Autoriser les en-têtes requis
	    cors.setExposedHeaders(Arrays.asList("Authorization")); // Exposer l'en-tête Authorization
	    cors.setAllowCredentials(true);

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", cors);
	    return source;
    }
}
