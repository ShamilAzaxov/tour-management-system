package sh.tech.tourmanagementsystem.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sh.tech.tourmanagementsystem.dao.entity.Guide;
import sh.tech.tourmanagementsystem.dao.entity.Tour;
import sh.tech.tourmanagementsystem.dao.repository.DestinationRepository;
import sh.tech.tourmanagementsystem.dao.repository.GuideRepository;
import sh.tech.tourmanagementsystem.dao.repository.TourRepository;
import sh.tech.tourmanagementsystem.dto.request.tour.SaveTourRequest;
import sh.tech.tourmanagementsystem.dto.request.tour.UpdateTourRequest;
import sh.tech.tourmanagementsystem.dto.response.DestinationResponse;
import sh.tech.tourmanagementsystem.dto.response.GuideResponse;
import sh.tech.tourmanagementsystem.dto.response.TourResponse;
import sh.tech.tourmanagementsystem.dto.response.TravelerResponse;
import sh.tech.tourmanagementsystem.exception.GuideISBusyException;
import sh.tech.tourmanagementsystem.exception.NotFoundException;
import sh.tech.tourmanagementsystem.mapper.DestinationMapper;
import sh.tech.tourmanagementsystem.mapper.GuideMapper;
import sh.tech.tourmanagementsystem.mapper.TourMapper;
import sh.tech.tourmanagementsystem.mapper.TravelerMapper;
import sh.tech.tourmanagementsystem.service.inter.TourService;

import java.util.List;

import static sh.tech.tourmanagementsystem.dto.enums.ExceptionConstant.*;
import static sh.tech.tourmanagementsystem.dto.enums.GuideStatus.BUSY;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TourServiceImpl implements TourService {
    TourRepository tourRepository;
    TourMapper tourMapper;
    GuideRepository guideRepository;
    DestinationMapper destinationMapper;
    DestinationRepository destinationRepository;
    TravelerMapper travelerMapper;
    GuideMapper guideMapper;

    @Override
    public TourResponse saveTour(SaveTourRequest saveTourRequest) {
        Tour tour = tourMapper.toEntity(saveTourRequest);
        tourRepository.save(tour);
        return tourMapper.toDto(tour);
    }

    @Override
    public List<TourResponse> getAllTours() {
        return tourRepository.findAll().stream().map(tourMapper::toDto).toList();
    }

    @Override
    public TourResponse getTourById(Long id) {
        return tourMapper.toDto(tourRepository.findById(id).orElseThrow(() ->
                new NotFoundException(TOUR_NOT_FOUND.getCode(), TOUR_NOT_FOUND.getMessage() + " by id: " + id)));
    }

    @Override
    public void deleteTourById(Long id) {
        fetchIfTourExists(id);
        tourRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void assignGuideToTour(Long guideId, Long tourId) {
        Guide guide = guideRepository.findById(guideId).orElseThrow(() ->
                new NotFoundException(GUIDE_NOT_FOUND.getCode(), GUIDE_NOT_FOUND.getMessage() + " by id: " + guideId));

        if (guide.getStatus().equals(BUSY))
            throw new GuideISBusyException(GUIDE_IS_BUSY.getCode(), GUIDE_IS_BUSY.getMessage() + " by id: " + guideId);

        Tour tour = tourRepository.findById(tourId).orElseThrow(() ->
                new NotFoundException(TOUR_NOT_FOUND.getCode(), TOUR_NOT_FOUND.getMessage() + " by id: " + tourId));

        tour.getGuides().add(guide);
        guide.setStatus(BUSY);
        guideRepository.save(guide);
    }

    @Override
    public TourResponse updateTour(UpdateTourRequest updateTourRequest) {
        fetchIfTourExists(updateTourRequest.getId());
        Tour tour = tourMapper.toEntity(updateTourRequest);
        return tourMapper.toDto(tourRepository.save(tour));
    }

    @Override
    public List<DestinationResponse> getAllDestinationsByTourId(Long tourId) {
        String tourName = tourRepository.findById(tourId).orElseThrow(() ->
                new NotFoundException(TOUR_NOT_FOUND.getCode(), TOUR_NOT_FOUND.getMessage())).getName();
        List<DestinationResponse> destinationResponses = destinationRepository.findByTourId(tourId).stream().map(destinationMapper::toDto).toList();
                destinationResponses.forEach(destination -> destination.setTourName(tourName));
        return destinationResponses;
    }

    @Override
    public List<TravelerResponse> getTravelersByTourId(Long tourId) {
        return tourRepository.findTravelersByTourId(tourId).stream().map(travelerMapper::toDto).toList();
    }

    @Override
    public List<GuideResponse> getGuidesByTourId(Long tourId) {
        return tourRepository.findGuideByTourId(tourId).stream().map(guideMapper::toDto).toList();
    }

    private void fetchIfTourExists(Long id) {
        if (!tourRepository.existsById(id))
            throw new NotFoundException(TOUR_NOT_FOUND.getCode(),
                    TOUR_NOT_FOUND.getMessage() + " by id: " + id);
    }
}