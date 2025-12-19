package com.example.journal.service;

import com.example.journal.entity.UserEntity;
import com.example.journal.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepo.findByUserName(username);
        if(userEntity != null) {
            return User.builder().username(userEntity.getUserName())
                    .password(userEntity.getPassword())
                    .roles(userEntity.getRoles()).build();
        }
        throw new UsernameNotFoundException("user not found "+username);
    }
}
