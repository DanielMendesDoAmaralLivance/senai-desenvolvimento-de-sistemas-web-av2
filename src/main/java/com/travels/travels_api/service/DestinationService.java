package com.travels.travels_api.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.travels.travels_api.entity.Destination;

@Service
public class DestinationService {
    private final Map<Long, Destination> destinations = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public Destination create(Destination destination) {
        destination.setId(idGenerator.incrementAndGet());

        destination.setAvgRating(0);
        destination.setTotalRatings(0);

        destinations.put(destination.getId(), destination);

        return destination;
    }
}
