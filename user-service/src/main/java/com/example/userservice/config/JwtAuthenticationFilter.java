package com.example.userservice.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// Es un componente para se detectado en el contexto de spring.
// Función principal es verificar la presencia y validar el JWT que viene en las peticiones.
// Si es válido, lo suma al usuario al contexto de Spring
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;
    // El UserDetailsService es el que se encarga de cargar los datos de usuario,
    // como su nombre y credenciales.
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        // Permite el acceso a urls publicas sin autorizacion requerida
        if (requestURI.startsWith("/public/")) {
            chain.doFilter(request, response);
            return;
        }

        String header = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7);
            try {
                username = jwtUtils.extractUsername(token);
            } catch (Exception e) {
                logger.error("Error extracting username from token", e);
            }
        }
        // getAutentication chequea que no haya nadie autenticado en el momento
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtUtils.validateToken(token, userDetails.getUsername())) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                // Cargo los detalles de la autenticación para esta request
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // A la nueva autenticacion la cargamos en el contexto, para que en el controlador, a traves
                // de la autenticacion podamos obtener los datos de usuario
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
    }
}