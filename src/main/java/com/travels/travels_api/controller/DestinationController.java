package com.travels.travels_api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travels.travels_api.dto.destination.RateRequestDTO;
import com.travels.travels_api.entity.Destination;
import com.travels.travels_api.service.DestinationService;

@RestController
@RequestMapping("/api/destinations")
public class DestinationController {
    @Autowired
    private DestinationService destinationService;

    @GetMapping
    public List<Destination> get(@RequestParam(required = false) String searchTerm) {
        return destinationService.list(Optional.ofNullable(searchTerm));
    }

    @GetMapping("/{id}")
    public Optional<Destination> getById(@PathVariable Long id) {
        return destinationService.get(id);
    }

    @PostMapping
    public Destination post(@RequestBody Destination destination) {
        return destinationService.create(destination);
    }

    @PatchMapping("/{id}/rate")
    public Optional<Destination> rate(@PathVariable Long id, @RequestBody RateRequestDTO request) {
        return destinationService.rate(id, request.getNote());
    }
}
