package com.example.cms.security.auth;

import com.example.cms.dao.TeacherDAO;
import com.example.cms.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.example.cms.dao.StudentDAO;
import com.example.cms.entity.User;

/**
 * @author xzy
 * @date 2018-12-18
 */
@Service
@Component
@ComponentScan({"com.example.cms.dao"})
@Qualifier("customUserDetailsService")
public class UserDetailsService {
    @Autowired
    private UserDAO userDAO;


    @Value("${program.mp.webId}")
    private String webId;

    @Value("${program.mp.secret-key}")
    private String secret;
    private static final String KEY_OPEN_ID = "openId";
    private static final String KEY_ERR_CODE = "errCode";


    /**
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
            return getUserByAccount(username);
    }

    private User getUserByAccount(String account)
            throws UsernameNotFoundException {
        User user = userDAO.getByAccount(account);
        if (user == null) {
            throw new UsernameNotFoundException("user name not found.");

        }

        return user;
    }
}
