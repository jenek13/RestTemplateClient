package com.ten.security.service;

import com.ten.dto.UserDTO;
import com.ten.service.UserDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {	//сервис, отвечающий за получение аутентификации пользователя

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    private final UserService userService;
    private final UserDTOService userDTOService;

    @Autowired
    public UserDetailsServiceImpl(UserDTOService userDTOService) {
        this.userDTOService = userDTOService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserDTO userDTO = userDTOService.getUserByLogin(login);
        if (userDTO == null) {
            throw new UsernameNotFoundException("Username " + login + " not found");
        }
        return  userDTO;
    }


}