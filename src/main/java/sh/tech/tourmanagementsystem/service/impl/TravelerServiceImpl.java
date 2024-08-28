package sh.tech.tourmanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sh.tech.tourmanagementsystem.dao.entity.Tour;
import sh.tech.tourmanagementsystem.dao.entity.Traveler;
import sh.tech.tourmanagementsystem.dao.repository.TourRepository;
import sh.tech.tourmanagementsystem.dao.repository.TravelerRepository;
import sh.tech.tourmanagementsystem.dto.request.traveler.SaveTravelerRequest;
import sh.tech.tourmanagementsystem.dto.request.traveler.UpdateTravelerRequest;
import sh.tech.tourmanagementsystem.dto.response.TourResponse;
import sh.tech.tourmanagementsystem.dto.response.TravelerResponse;
import sh.tech.tourmanagementsystem.exception.NotFoundException;
import sh.tech.tourmanagementsystem.mapper.TourMapper;
import sh.tech.tourmanagementsystem.mapper.TravelerMapper;
import sh.tech.tourmanagementsystem.service.inter.TravelerService;

import java.util.List;

import static sh.tech.tourmanagementsystem.dto.enums.ExceptionConstant.TOUR_NOT_FOUND;
import static sh.tech.tourmanagementsystem.dto.enums.ExceptionConstant.TRAVELER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class TravelerServiceImpl implements TravelerService {
    private final TravelerRepository travelerRepository;
    private final TravelerMapper travelerMapper;
    private final TourRepository tourRepository;
    private final TourMapper tourMapper;

    @Override
    @Transactional
    public TravelerResponse saveTraveler(SaveTravelerRequest saveTravelerRequest) {
        Traveler traveler = travelerMapper.toEntity(saveTravelerRequest);
        return travelerMapper.toDto(travelerRepository.save(traveler));
    }

    @Override
    public TravelerResponse getTravelerById(Long id) {
        return travelerMapper.toDto(travelerRepository.findById(id).orElseThrow(
                () -> new NotFoundException(TRAVELER_NOT_FOUND.getCode(), TRAVELER_NOT_FOUND.getMessage())
        ));
    }

    @Override
    public List<TravelerResponse> getAllTravelers() {
        return travelerRepository.findAll().stream().map(travelerMapper::toDto).toList();
    }

    @Override
    public void deleteTraveler(Long id) {
        fetchIfTravelerExists(id);
        travelerRepository.deleteById(id);
    }

    @Override
    public TravelerResponse updateTraveler(UpdateTravelerRequest updateTravelerRequest) {
        Traveler traveler = travelerMapper.toEntity(updateTravelerRequest);
        return travelerMapper.toDto(travelerRepository.save(traveler));
    }

    @Override
    public List<TourResponse> getToursByTravelerId(Long travelerId) {
        return travelerRepository.findToursByTravelerId(travelerId).stream().map(tourMapper::toDto).toList();
    }

    @Override
    @Transactional
    public void assignTravelerToTour(Long tourId, Long travelerId) {
        Tour tour = tourRepository.findById(tourId).orElseThrow(() ->
                new NotFoundException(TOUR_NOT_FOUND.getCode(), TOUR_NOT_FOUND.getMessage()));
        Traveler traveler = travelerRepository.findById(travelerId).orElseThrow(() ->
                new NotFoundException(TRAVELER_NOT_FOUND.getCode(), TRAVELER_NOT_FOUND.getMessage()));
        tour.getTravelers().add(traveler);
        travelerRepository.save(traveler);
    }

    public void fetchIfTravelerExists(Long id) {
        if (!travelerRepository.existsById(id))
            throw new NotFoundException(TRAVELER_NOT_FOUND.getCode(),
                    TRAVELER_NOT_FOUND.getMessage() + " by id: " + id);
    }
}
