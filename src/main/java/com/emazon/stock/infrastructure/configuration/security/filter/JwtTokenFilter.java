package com.emazon.stock.infrastructure.configuration.security.filter;

import com.emazon.stock.infrastructure.configuration.security.MyUserDetailsService;
import com.emazon.stock.infrastructure.configuration.util.JwtTokenFilterConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final MyUserDetailsService myUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response,@NonNull FilterChain filterChain) throws ServletException, IOException {
         String authHeader = request.getHeader(JwtTokenFilterConstants.AUTH_HEADER);

         if (authHeader == null || !authHeader.startsWith(JwtTokenFilterConstants.TOKEN_PREFIX)) {
             filterChain.doFilter(request, response);
             return;
         }
        String jwt = authHeader.substring(JwtTokenFilterConstants.TOKEN_PREFIX_LENGTH);
        UserDetails user = myUserDetailsService.loadUserByUsername(jwt);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, jwt, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);
    }
}
