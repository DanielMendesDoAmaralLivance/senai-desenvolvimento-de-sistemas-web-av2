package com.travels.travels_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travels.travels_api.entity.ApplicationUser;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    Optional<ApplicationUser> findByEmail(String email);
}
