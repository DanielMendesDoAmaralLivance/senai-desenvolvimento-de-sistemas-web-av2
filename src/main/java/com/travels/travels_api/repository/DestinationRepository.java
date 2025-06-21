package com.travels.travels_api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.travels.travels_api.entity.Destination;

public interface DestinationRepository extends CrudRepository<Destination, Long> { 
    List<Destination> findAll();
}
