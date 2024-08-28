package sh.tech.tourmanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sh.tech.tourmanagementsystem.dao.entity.Destination;
import sh.tech.tourmanagementsystem.dao.entity.Tour;
import sh.tech.tourmanagementsystem.dao.repository.DestinationRepository;
import sh.tech.tourmanagementsystem.dao.repository.TourRepository;
import sh.tech.tourmanagementsystem.dto.request.destination.SaveDestinationRequest;
import sh.tech.tourmanagementsystem.dto.request.destination.UpdateDestinationRequest;
import sh.tech.tourmanagementsystem.dto.response.DestinationResponse;
import sh.tech.tourmanagementsystem.exception.NotFoundException;
import sh.tech.tourmanagementsystem.mapper.DestinationMapper;
import sh.tech.tourmanagementsystem.service.inter.DestinationService;

import java.util.List;
import java.util.Objects;

import static sh.tech.tourmanagementsystem.dto.enums.ExceptionConstant.DESTINATION_NOT_FOUND;
import static sh.tech.tourmanagementsystem.dto.enums.ExceptionConstant.TOUR_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class DestinationServiceImpl implements DestinationService {
    private final DestinationRepository destinationRepository;
    private final DestinationMapper destinationMapper;
    private final TourRepository tourRepository;

    @Override
    public List<DestinationResponse> getAllDestinations() {

        List<Destination> destinations = destinationRepository.findAll();

        return destinations.stream().map(destinationMapper::toDto)
                        .peek(destinationResponse -> destinations.forEach(destination -> {
                    if (Objects.equals(destination.getId(), destinationResponse.getId())) {
                        destinationResponse.setTourName(destination.getTour().getName());
                    }})).toList();
    }

    @Override
    public DestinationResponse getDestinationById(Long id) {
        Destination destination = destinationRepository.findById(id).orElseThrow(() ->
                new NotFoundException(DESTINATION_NOT_FOUND.getCode(), DESTINATION_NOT_FOUND.getMessage()));
        DestinationResponse destinationResponse = destinationMapper.toDto(destination);
        destinationResponse.setTourName(destination.getTour().getName());
        return destinationResponse;
    }

    @Override
    public DestinationResponse saveDestination(SaveDestinationRequest saveDestinationRequest) {
        Destination destination = destinationMapper.toEntity(saveDestinationRequest);
        Tour tour = tourRepository.findById(saveDestinationRequest.getTourId())
                .orElseThrow(() -> new NotFoundException(TOUR_NOT_FOUND.getCode(), TOUR_NOT_FOUND.getMessage()));
        destination.setTour(tour);
        destinationRepository.save(destination);
        DestinationResponse destinationResponse = destinationMapper.toDto(destination);
        destinationResponse.setTourName(destination.getTour().getName());
        return destinationResponse;
    }

    @Override
    public DestinationResponse updateDestination(UpdateDestinationRequest updateDestinationRequest) {
        fetchIfExistsDestination(updateDestinationRequest.getId());
        Destination destination = destinationMapper.toEntity(updateDestinationRequest);
        Tour tour = tourRepository.findById(updateDestinationRequest.getTourId()).orElseThrow(() ->
                new NotFoundException(TOUR_NOT_FOUND.getCode(), TOUR_NOT_FOUND.getMessage()));
        destination.setTour(tour);
        destinationRepository.save(destination);
        DestinationResponse destinationResponse = destinationMapper.toDto(destination);
        destinationResponse.setTourName(tour.getName());
        return destinationResponse;
    }

    @Override
    public void deleteDestinationById(Long id) {
        fetchIfExistsDestination(id);
        destinationRepository.deleteById(id);
    }

    private void fetchIfExistsDestination(Long id) {
        if (!destinationRepository.existsById(id))
            throw new NotFoundException(DESTINATION_NOT_FOUND.getCode(),
                    DESTINATION_NOT_FOUND.getMessage() + " by id: " + id);
    }
}
