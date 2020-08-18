package com.example.cms.security.auth.filter;

import com.example.cms.exception.UserNotFoundException;
import com.example.cms.security.auth.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.example.cms.entity.User;
import com.example.cms.vo.LoginSuccessVO;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;
import java.util.Map;

/**
 *对登录请求进行拦截。即 POST /login
 * @author xzy
 * @date 2018-12-19
 */
public class LoginFilter extends AbstractAuthenticationProcessingFilter  {
    @Autowired
    private JwtService jwtService;

    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    public LoginFilter() {
        super(new AntPathRequestMatcher("/user/login", "POST"));
    }

    /**
     * 尝试登录
     *
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        String post="POST";
        if (!request.getMethod().equals(post)) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        String username = null;
        String password = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map map;
            map = objectMapper.readValue(request.getInputStream(), Map.class);
            String account="account";
            String passWord="password";
            String code="code";
            if (map.get(account) != null) {
                username = map.get(account).toString();
                password = map.get(passWord).toString();
            } else if (map.get(code) != null) {
                username = "code " + map.get("code");
            }
        } catch (Exception e) {

        }

        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }

        username = username.trim();

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                username, password);

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    /**
     * 登录成功， 返回 jwt 等信息
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authResult;
        User user = (User) auth.getDetails();
        String jwtString = jwtService.generateJwt(user);

        LoginSuccessVO vo = new LoginSuccessVO();
        vo.setJwt(jwtString);
        vo.setAccount(user.getAccount());
        vo.setRole(user.getRole() == null ? "unbind" : (user.getRole() == 1 ? "teacher" : "student"));
        vo.setName(user.getName());
        vo.setId(user.getId());
        vo.setIsActive(user.getIsActive());

        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(objectMapper.writeValueAsString(vo));


    }
    /**
     * 登录失败 返回 400 状态码
     *
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

        response.setStatus(400);
    }
    protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }
}
