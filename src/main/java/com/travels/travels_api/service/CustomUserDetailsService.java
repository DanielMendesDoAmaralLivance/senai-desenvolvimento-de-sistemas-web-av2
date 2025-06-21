package com.travels.travels_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import com.travels.travels_api.entity.ApplicationUser;
import com.travels.travels_api.exception.BadRequestHttpException;
import com.travels.travels_api.repository.ApplicationUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private ApplicationUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws BadRequestHttpException {
        ApplicationUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestHttpException("None user with this username has found."));

        return User.withUsername(user.getEmail())
                .password(user.getPassword())
                .roles("ROLE_" + user.getRole().name())
                .build();
    }
}
