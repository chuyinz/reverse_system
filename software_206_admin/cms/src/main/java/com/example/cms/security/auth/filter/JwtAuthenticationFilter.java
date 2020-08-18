package com.example.cms.security.auth.filter;


import com.example.cms.entity.Admin;
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

    private static final String ROLE_ADMIN = "ADMIN";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String tokenHeader = "Bearer ";
        if (authHeader != null && authHeader.startsWith(tokenHeader)) {
            String authToken = authHeader.substring(tokenHeader.length());
            JwtPayload jwtPayload = jwtService.verifyJwt(authToken);
            if (jwtPayload != null && SecurityContextHolder.getContext().getAuthentication() == null
                    && jwtPayload.getExp() > System.currentTimeMillis()) {
                Admin admin = jwtPayload.toAdmin();
                UsernamePasswordAuthenticationToken authentication;
                    authentication = new UsernamePasswordAuthenticationToken(admin.getAccount(),
                            null, getAuthorities());
                authentication.setDetails(admin);
                request.setAttribute("admin", admin);
                request.setAttribute("adminId", admin.getId());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    private Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(ROLE_ADMIN));
        return authorities;
    }
}
