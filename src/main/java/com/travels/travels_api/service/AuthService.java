package com.travels.travels_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.travels.travels_api.dto.auth.RegisterRequestDTO;
import com.travels.travels_api.entity.ApplicationUser;
import com.travels.travels_api.enums.Role;
import com.travels.travels_api.exception.BadRequestHttpException;
import com.travels.travels_api.repository.ApplicationUserRepository;

@Service
public class AuthService {
    @Autowired
    private ApplicationUserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public ApplicationUser register(RegisterRequestDTO request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new BadRequestHttpException("The email already is in use.");
        }

        ApplicationUser user = new ApplicationUser();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.CLIENT);

        return userRepository.save(user);
    }
}
