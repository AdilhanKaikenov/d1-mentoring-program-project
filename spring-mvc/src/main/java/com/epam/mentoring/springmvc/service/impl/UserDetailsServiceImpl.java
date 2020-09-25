package com.epam.mentoring.springmvc.service.impl;

import com.epam.mentoring.springmvc.repository.UserRepository;
import com.epam.mentoring.springmvc.vo.authentication.UserDetailsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Kaikenov Adilhan
**/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        UserDetailsVO user = this.userRepository.findSecurityDetailsByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found: " + username);
        }

        return user;
    }
}
