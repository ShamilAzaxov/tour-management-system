package sh.tech.tourmanagementsystem.service.inter;

import sh.tech.tourmanagementsystem.dto.request.tour.SaveTourRequest;
import sh.tech.tourmanagementsystem.dto.request.tour.UpdateTourRequest;
import sh.tech.tourmanagementsystem.dto.response.DestinationResponse;
import sh.tech.tourmanagementsystem.dto.response.GuideResponse;
import sh.tech.tourmanagementsystem.dto.response.TourResponse;
import sh.tech.tourmanagementsystem.dto.response.TravelerResponse;

import java.util.List;

public interface TourService {
    TourResponse saveTour(SaveTourRequest saveTourRequest);
    List<TourResponse> getAllTours();
    TourResponse getTourById(Long id);
    void deleteTourById(Long id);
    void assignGuideToTour(Long guideId, Long tourId);
    TourResponse updateTour(UpdateTourRequest updateTourRequest);
    List<DestinationResponse> getAllDestinationsByTourId(Long tourId);
    List<TravelerResponse> getTravelersByTourId(Long tourId);
    List<GuideResponse> getGuidesByTourId(Long tourId);

}
