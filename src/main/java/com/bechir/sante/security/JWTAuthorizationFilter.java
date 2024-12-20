package com.bechir.sante.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.exceptions.JWTVerificationException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
    
    private static final Logger logger = LoggerFactory.getLogger(JWTAuthorizationFilter.class);
    private Algorithm algorithm;

    public JWTAuthorizationFilter() {
        try {
            this.algorithm = Algorithm.HMAC256(SecParams.SECRET);
        } catch (IllegalArgumentException e) {
            logger.error("Erreur lors de l'initialisation de l'algorithme JWT", e);
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = request.getHeader("Authorization");
            
            // Si pas de token, on continue la chaîne
            if (jwt == null || !jwt.startsWith(SecParams.PREFIX)) {
                filterChain.doFilter(request, response);
                return;
            }

            // Vérification du token
            JWTVerifier verifier = JWT.require(algorithm)
                                    .build();
            
            // Suppression du préfixe "Bearer "
            jwt = jwt.substring(SecParams.PREFIX.length());
            
            try {
                DecodedJWT decodedJWT = verifier.verify(jwt);
                String username = decodedJWT.getSubject();
                List<String> roles = decodedJWT.getClaims().get("roles").asList(String.class);
                
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                for (String r : roles) {
                    authorities.add(new SimpleGrantedAuthority(r));
                }
                
                UsernamePasswordAuthenticationToken authentication = 
                    new UsernamePasswordAuthenticationToken(username, null, authorities);
                    
                SecurityContextHolder.getContext().setAuthentication(authentication);
                
            } catch (JWTVerificationException e) {
                logger.error("Token JWT invalide: {}", e.getMessage());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            
        } catch (Exception e) {
            logger.error("Erreur lors du traitement du token JWT", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        
        filterChain.doFilter(request, response);
    }
}