package com.aldinalj.enterprise_project.config.security;


import com.aldinalj.enterprise_project.user.dao.UserDAO;
import com.aldinalj.enterprise_project.user.model.CustomUser;
import com.aldinalj.enterprise_project.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDAO userDAO;

    @Autowired
    public CustomUserDetailsService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        CustomUser customUser = userDAO
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        System.out.println("LOADING USER TO SPRING... " + customUser);
        return new CustomUserDetails(customUser);
    }
}
