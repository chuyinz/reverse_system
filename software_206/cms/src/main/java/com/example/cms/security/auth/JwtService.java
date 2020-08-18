package com.example.cms.security.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.example.cms.entity.User;
import com.example.cms.security.auth.JwtPayload;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xzy
 * @date 2018-12-17
 */
@Service
public class JwtService {

    @Value("234534")
    private String secret;

    private Long expireTime = 24 * 60 * 60 * 1000L;
    /**
     * 固定的 JWT header
     */
    private static final Map<String, String> JWT_HEADER =
            new HashMap<String, String>() {{
                put("alg", "HS256");
                put("typ", "JWT");
            }};

    /**
     * 从登录成功的用户信息生成 JWT
     *
     * @param user
     * @return token
     */
    public String generateJwt(User user) {
        ObjectMapper objectMapper = new ObjectMapper();
        JwtPayload jwtPayload = new JwtPayload(user.getId(),user.getAccount(),
                user.getRole() == null ? "unbind" : (user.getRole() == 1 ? "teacher" : "student"),
                user.getName(),user.getIsActive(), System.currentTimeMillis() + expireTime);
        try {
            String header = objectMapper.writeValueAsString(JWT_HEADER);
            String payload = objectMapper.writeValueAsString(jwtPayload);
            return generateJwt(header, payload);
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 由 JWT 的 header 和 payload 生成含有 signature 的 JWT
     *
     * @param header
     * @param payload
     * @return
     */
    private String generateJwt(String header, String payload) {
        String base64header = base64encode(header);
        String base64payload = base64encode(payload);
        String signature = generateSignature(base64header, base64payload);
        return base64header + '.' + base64payload + '.' + signature;
    }

    private String base64encode(String src) {
        return Base64.getEncoder().encodeToString(src.getBytes());
    }

    private String base64decode(String src) {
        return new String(Base64.getDecoder().decode(src));
    }

    /**
     * 由 JWT 的 header 和 payload 生成 signature
     *
     * @param header
     * @param payload
     * @return
     */
    private String generateSignature(String header, String payload) {
        String signature = null;
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKey secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            mac.init(secretKey);
            String src = header + '.' + payload;
            signature = Base64.getEncoder().encodeToString(mac.doFinal(src.getBytes()));
        } catch (Exception e) {

        }
        return signature;
    }

    /**
     * 验证JWT并返回结果
     *
     * @param jwtString 需验证的JWT
     * @return成功返回JWT，错误返回null
     */
    public JwtPayload verifyJwt(String jwtString) {

        String[] t = jwtString.split("\\.");
        int len=3;
        if (t.length != len) {
            return null;
        }
        String headerString = t[0];
        String payloadString = t[1];
        String signature = t[2];
        if (signature.equals(generateSignature(headerString, payloadString)) == false) {
            return null;
        }
        User user = new User();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            payloadString = base64decode(payloadString);
            JwtPayload jwtPayload = objectMapper.readValue(payloadString.getBytes(), JwtPayload.class);
            return jwtPayload;
        } catch (Exception e) {
            return null;
        }

    }
}
