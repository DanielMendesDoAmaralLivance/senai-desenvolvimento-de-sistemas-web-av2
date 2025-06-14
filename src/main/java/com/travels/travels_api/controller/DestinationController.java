package com.travels.travels_api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping
    public Destination post(@RequestBody Destination destination) {
        return destinationService.create(destination);
    }
}
