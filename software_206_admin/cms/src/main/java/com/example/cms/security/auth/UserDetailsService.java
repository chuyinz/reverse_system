package com.example.cms.security.auth;

import com.example.cms.dao.AdminDAO;
import com.example.cms.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
    private AdminDAO adminDAO;


    @Value("${program.mp.webId}")
    private String webId;

    @Value("${program.mp.secret-key}")
    private String secret;

    /**
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    public Admin loadUserByUsername(String username) throws UsernameNotFoundException {
            return getUserByAccount(username);
    }

    private Admin getUserByAccount(String account)
            throws UsernameNotFoundException {
        Admin admin = adminDAO.getByAccount(account);
        if (admin == null) {
            throw new UsernameNotFoundException("user name not found.");

        }

        return admin;
    }
}
