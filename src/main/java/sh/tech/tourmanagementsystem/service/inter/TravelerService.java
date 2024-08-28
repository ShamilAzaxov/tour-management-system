package sh.tech.tourmanagementsystem.service.inter;

import sh.tech.tourmanagementsystem.dto.request.traveler.SaveTravelerRequest;
import sh.tech.tourmanagementsystem.dto.request.traveler.UpdateTravelerRequest;
import sh.tech.tourmanagementsystem.dto.response.TourResponse;
import sh.tech.tourmanagementsystem.dto.response.TravelerResponse;

import java.util.List;

public interface TravelerService {
    TravelerResponse saveTraveler(SaveTravelerRequest saveTravelerRequest);
    TravelerResponse getTravelerById(Long id);
    List<TravelerResponse> getAllTravelers();
    void deleteTraveler(Long id);
    TravelerResponse updateTraveler(UpdateTravelerRequest updateTravelerRequest);
    List<TourResponse> getToursByTravelerId(Long travelerId);
    void assignTravelerToTour(Long tourId, Long travelerId);
}