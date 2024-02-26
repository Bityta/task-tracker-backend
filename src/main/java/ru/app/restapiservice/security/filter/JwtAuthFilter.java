package ru.app.restapiservice.security.filter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.app.restapiservice.security.service.JwtService;
import ru.app.restapiservice.security.service.UserDetailsServiceImp;

import java.io.IOException;

/**
 * Filter class to handle JWT authentication.
 */
@RequiredArgsConstructor
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthFilter.class);
    private static final String HEADER = "Authorization";
    private static final String HEADER_CODE = "Bearer ";
    private final JwtService jwtService;
    private final UserDetailsServiceImp userDetailsService;

    /**
     * Performs JWT authentication for each incoming request.
     *
     * @param request     HTTP request object
     * @param response    HTTP response object
     * @param filterChain Filter chain for handling requests
     * @throws ServletException if an error occurs during the servlet execution
     * @throws IOException      if an I/O error occurs while processing the request
     */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader(HEADER);

        if (authHeader == null || !authHeader.startsWith(HEADER_CODE)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(HEADER_CODE.length());

        String email;

        try {
            email = jwtService.extractEmail(token);
        } catch (ExpiredJwtException ex) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT token has expired");
            LOGGER.error("User authorization error. {}", ex.getMessage());
            return;
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException ex) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "JWT token is invalid");
            LOGGER.error("User authorization error. {}", ex.getMessage());
            return;
        }

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);

            if (this.jwtService.isValid(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
