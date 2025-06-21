package com.travels.travels_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travels.travels_api.entity.Destination;
import com.travels.travels_api.exception.BadRequestHttpException;
import com.travels.travels_api.exception.NotFoundHttpException;
import com.travels.travels_api.repository.DestinationRepository;

@Service
public class DestinationService {
    @Autowired
    private DestinationRepository destinationRepository;

    public List<Destination> list(Optional<String> searchTerm) {
        if (!searchTerm.isPresent()) {
            return destinationRepository.findAll();
        }

        String searchTermNormalized = searchTerm.get().toLowerCase();

        return destinationRepository.findByNameContainingIgnoreCaseOrLocationContainingIgnoreCase(searchTermNormalized, searchTermNormalized);
    }

    public Destination get(Long id) {
        Optional<Destination> result = destinationRepository.findById(id);

        if (!result.isPresent()) {
            throw new NotFoundHttpException("Destination is not found");
        }

        Destination destination = result.get();

        return destination;
    }

    public Destination create(Destination destination) {
        destination.setAvgRating(0);
        destination.setTotalRatings(0);

        destinationRepository.save(destination);

        return destination;
    }

    public Destination rate(Long id, int note) {
        if (note < 1 || note > 10) {
            throw new BadRequestHttpException("Note rate must be between 1 and 10");
        }

        Optional<Destination> result = destinationRepository.findById(id);

        if (!result.isPresent()) {
            throw new NotFoundHttpException("Destination is not found");
        }

        Destination destination = result.get();

        double destinationAvgRating = destination.getAvgRating();
        int destinationTotalRatings = destination.getTotalRatings();

        double newAvgRating = (destinationAvgRating * destinationTotalRatings) + note;
        int newTotalRatings = destinationTotalRatings + 1;

        double newAvg = newAvgRating / newTotalRatings;

        destination.setAvgRating(newAvg);
        destination.setTotalRatings(newTotalRatings);

        destinationRepository.save(destination);

        return destination;
    }

    public void delete(Long id) {
        destinationRepository.deleteById(id);
    }
}
