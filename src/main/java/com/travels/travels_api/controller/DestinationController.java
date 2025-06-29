package com.travels.travels_api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travels.travels_api.dto.api.Response;
import com.travels.travels_api.dto.api.ResponseData;
import com.travels.travels_api.dto.destination.RateRequestDTO;
import com.travels.travels_api.entity.Destination;
import com.travels.travels_api.service.DestinationService;

@RestController
@RequestMapping("/api/destinations")
public class DestinationController {
    @Autowired
    private DestinationService destinationService;

    @GetMapping
    public ResponseEntity<ResponseData<List<Destination>>> get(@RequestParam(required = false) String searchTerm) {
        List<Destination> result = destinationService.list(Optional.ofNullable(searchTerm));

        return ResponseEntity.ok(new ResponseData<List<Destination>>(result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Destination>> getById(@PathVariable Long id) {
        Destination result = destinationService.get(id);
        return ResponseEntity.ok(new ResponseData<Destination>(result));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseData<Destination>> post(@RequestBody Destination destination) {
        Destination result = destinationService.create(destination);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseData<Destination>(result));
    }

    @PatchMapping("/{id}/rate")
    public ResponseEntity<ResponseData<Destination>> rate(
        @PathVariable Long id,
        @RequestBody RateRequestDTO request
    ) {
        Destination result = destinationService.rate(id, request.getNote());
        return ResponseEntity.ok(new ResponseData<Destination>(result));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response> delete(@PathVariable Long id) {
        destinationService.delete(id);
        return ResponseEntity.ok(new Response());
    }
}
