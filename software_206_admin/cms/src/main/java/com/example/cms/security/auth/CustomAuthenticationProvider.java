package com.example.cms.security.auth;

import com.example.cms.entity.Admin;
import com.example.cms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/**
 * @author xzy
 * @date 2018-12-18
 * CustomAuthenticationProvider提供登录验证处理逻辑(处理认证请求)
 */
@Component
@Qualifier("customAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider{
    private static final String ROLE_ADMIN = "ROLE_ADMIN";

    @Autowired
    @Qualifier("customUserDetailsService")
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        Admin admin = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken auth;
         if (comparePassword(authentication.getCredentials().toString(), admin.getPassword())) {
            auth = new UsernamePasswordAuthenticationToken(username, null, getAuthorities());
            auth.setDetails(admin);
        } else {
            throw new BadCredentialsException("password error.");
        }
        return auth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        boolean a = authentication.equals(
                UsernamePasswordAuthenticationToken.class);
        return a;
    }

    private boolean comparePassword(String input, String trusted) {
        return trusted.equals(input);
    }

    private Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(ROLE_ADMIN));
        return authorities;
    }
}
