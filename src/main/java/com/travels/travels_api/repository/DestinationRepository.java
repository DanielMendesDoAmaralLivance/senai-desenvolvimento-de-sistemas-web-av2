package com.travels.travels_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travels.travels_api.entity.Destination;

public interface DestinationRepository extends JpaRepository<Destination, Long> { 
    List<Destination> findAll();

    List<Destination> findByNameContainingIgnoreCaseOrLocationContainingIgnoreCase(
        String searchTermName, 
        String searchTermLocation
    );
}
