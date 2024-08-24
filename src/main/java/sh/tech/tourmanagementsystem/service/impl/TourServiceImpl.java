package sh.tech.tourmanagementsystem.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sh.tech.tourmanagementsystem.dao.entity.Guide;
import sh.tech.tourmanagementsystem.dao.entity.Tour;
import sh.tech.tourmanagementsystem.dao.repository.GuideRepository;
import sh.tech.tourmanagementsystem.dao.repository.TourRepository;
import sh.tech.tourmanagementsystem.dto.request.TourRequest;
import sh.tech.tourmanagementsystem.dto.response.TourResponse;
import sh.tech.tourmanagementsystem.exception.NotFoundException;
import sh.tech.tourmanagementsystem.mapper.TourMapper;
import sh.tech.tourmanagementsystem.service.inter.TourService;

import java.util.List;

import static sh.tech.tourmanagementsystem.dto.enums.ExceptionConstant.GUIDE_NOT_FOUND;
import static sh.tech.tourmanagementsystem.dto.enums.ExceptionConstant.TOUR_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class TourServiceImpl implements TourService {
    private final TourRepository tourRepository;
    private final TourMapper tourMapper;
    private final GuideRepository guideRepository;

    @Override
    public TourResponse saveUpdateTour(TourRequest tourRequest) {
        boolean ifExists = true;
        if (tourRequest.getId() != 0)
            ifExists = tourRepository.existsById(tourRequest.getId());
        Tour tour = null;
        if (ifExists) {
            tour = tourMapper.toEntity(tourRequest);
            tourRepository.save(tour);
        }
        else
            throw new NotFoundException(TOUR_NOT_FOUND.getCode(), TOUR_NOT_FOUND.getMessage());
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
        tourRepository.deleteById(id);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void assignGuideToTour(Long tourId, Long guideId) {
        Tour tour = tourRepository.findById(tourId).orElseThrow(() ->
                new NotFoundException(TOUR_NOT_FOUND.getCode(), TOUR_NOT_FOUND.getMessage()));
        Guide guide = guideRepository.findById(guideId).orElseThrow(() ->
                new NotFoundException(GUIDE_NOT_FOUND.getCode(), GUIDE_NOT_FOUND.getMessage()));
        tour.getGuides().add(guide);
        guide.getTours().add(tour);

        guideRepository.save(guide);
        tourRepository.save(tour);
    }
}