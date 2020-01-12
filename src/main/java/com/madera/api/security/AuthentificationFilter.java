package com.madera.api.security;

import com.madera.api.ApiApplication;
import com.madera.api.repository.UserRepository;

import org.jooq.DSLContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Classe pour filtrer les requêtes suivant l'état d'authentification
 * 
 * @author LADOUCE Fabien, CHEVALLIER Romain, HELIOT David
 * @version 1.0-PRE-RELEASE
 */
@Component
public class AuthentificationFilter extends OncePerRequestFilter {

    @Autowired
    DSLContext context;
    SecurityUser jwtUserDetails;

    private static final Logger log = LoggerFactory.getLogger(ApiApplication.class);
    final UserRepository userRepository = new UserRepository();

    /**
     *
     * @param request     HttpServletRequest
     * @param response    HttpServletResponse
     * @param filterChain FilterChain
     * @throws ServletException servletException
     * @throws IOException      IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Initialize
        final String headerToken = request.getHeader("Authorization");
        String token;
        SecurityUser securityUser = null;

        // Verify header
        if (headerToken != null && headerToken.startsWith("Bearer ")) {
            token = headerToken.substring(7);
            try {
                // Instantiate securityUser
                securityUser = userRepository.verifyTokenAndRole(context, token);
            } catch (Exception e) {
                log.error(e.getStackTrace() + "\n" + e.getMessage());
            }
        }
        // Verify securityUser
        if (securityUser != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    securityUser, null, securityUser.getAuthorities());
            // Set authenticated
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(request, response);
    }
}
