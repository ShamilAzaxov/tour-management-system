package sh.tech.tourmanagementsystem.service.inter;

import sh.tech.tourmanagementsystem.dto.request.TourRequest;
import sh.tech.tourmanagementsystem.dto.response.TourResponse;

import java.util.List;

public interface TourService {
    TourResponse saveUpdateTour(TourRequest tourRequest);
    List<TourResponse> getAllTours();
    TourResponse getTourById(Long id);
    void deleteTourById(Long id);
    void assignGuideToTour(Long tourId, Long guideId);

}
