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
import com.travels.travels_api.exception.BadRequestHttpException;
import com.travels.travels_api.exception.NotFoundHttpException;

@Service
public class DestinationService {
    private final Map<Long, Destination> destinations = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public List<Destination> list(Optional<String> searchTerm) {
        Collection<Destination> result = destinations.values();

        if (!searchTerm.isPresent()) {
            return new ArrayList<>(result);
        }

        String searchTermNormalized = searchTerm.get().toLowerCase();

        result = result.stream()
                .filter(d -> d.getName().toLowerCase().contains(searchTermNormalized) ||
                        d.getLocation().toLowerCase().contains(searchTermNormalized))
                .collect(Collectors.toList());

        return new ArrayList<>(result);
    }

    public Destination get(Long id) {
        Destination destination = destinations.get(id);

        if (destination == null) {

            throw new NotFoundHttpException("Destination is not found");
        }

        return destination;
    }

    public Destination create(Destination destination) {
        destination.setId(idGenerator.incrementAndGet());

        destination.setAvgRating(0);
        destination.setTotalRatings(0);

        destinations.put(destination.getId(), destination);

        return destination;
    }

    public Destination rate(Long id, int note) {
        if (note < 0 || note > 5) {
            throw new BadRequestHttpException("Note rate must be between 0 and 5");
        }

        Destination destination = destinations.get(id);

        if (destination == null) {
            throw new NotFoundHttpException("Destination is not found");
        }

        double destinationAvgRating = destination.getAvgRating();
        int destinationTotalRatings = destination.getTotalRatings();

        double newAvgRating = (destinationAvgRating * destinationTotalRatings) + note;
        int newTotalRatings = destinationTotalRatings + 1;

        double newAvg = newAvgRating / newTotalRatings;

        destination.setAvgRating(newAvg);
        destination.setTotalRatings(newTotalRatings);

        return destination;
    }

    public Destination delete(Long id) {
        Destination destination = destinations.remove(id);

        if (destination == null) {
            throw new NotFoundHttpException("Destination is not found");
        }

        return destination;
    }
}
