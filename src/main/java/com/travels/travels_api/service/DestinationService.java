package com.travels.travels_api.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.travels.travels_api.entity.Destination;

@Service
public class DestinationService {
    private final Map<Long, Destination> destinations = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public List<Destination> list(Optional<String> searchTerm) {
        Collection<Destination> result = destinations.values();

        if(!searchTerm.isPresent()) {
            return new ArrayList<>(result);
        }

        String searchTermNormalized = searchTerm.get().toLowerCase();

        result = result.stream()
            .filter(
                d -> d.getName().toLowerCase().contains(searchTermNormalized) ||
                     d.getLocation().toLowerCase().contains(searchTermNormalized)
            )
            .collect(Collectors.toList());

        return new ArrayList<>(result);
    }

    public Destination create(Destination destination) {
        destination.setId(idGenerator.incrementAndGet());

        destination.setAvgRating(0);
        destination.setTotalRatings(0);

        destinations.put(destination.getId(), destination);

        return destination;
    }
}
