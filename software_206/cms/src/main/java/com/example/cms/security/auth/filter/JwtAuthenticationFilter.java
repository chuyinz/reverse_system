package com.example.cms.security.auth.filter;


import com.example.cms.security.auth.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.cms.entity.User;
import com.example.cms.security.auth.JwtPayload;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author xzy
 * @date 2018-12-18
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
    @Autowired
    private JwtService jwtService;

    private static final String ROLE_STUDENT = "ROLE_STUDENT";
    private static final String ROLE_TEACHER = "ROLE_TEACHER";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String tokenHeader = "Bearer ";
        if (authHeader != null && authHeader.startsWith(tokenHeader)) {
            String authToken = authHeader.substring(tokenHeader.length());
            JwtPayload jwtPayload = jwtService.verifyJwt(authToken);
            if (jwtPayload != null && SecurityContextHolder.getContext().getAuthentication() == null
                    && jwtPayload.getExp() > System.currentTimeMillis()) {
                User user = jwtPayload.toUser();
                UsernamePasswordAuthenticationToken authentication;
                if (user.getRole() == null) {
                    authentication = new UsernamePasswordAuthenticationToken(user.getAccount(), null);
                } else {
                    authentication = new UsernamePasswordAuthenticationToken(user.getAccount(),
                            null, getAuthorities(user.getRole()));
                }
                authentication.setDetails(user);
                request.setAttribute("user", user);
                request.setAttribute("userId", user.getId());
                request.setAttribute("name", user.getName());
                request.setAttribute("role", user.getRole());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(
            int role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (role == 1) {
            authorities.add(new SimpleGrantedAuthority(ROLE_TEACHER));
        } else if (role == 0) {
            authorities.add(new SimpleGrantedAuthority(ROLE_STUDENT));
        }
        return authorities;
    }
}
