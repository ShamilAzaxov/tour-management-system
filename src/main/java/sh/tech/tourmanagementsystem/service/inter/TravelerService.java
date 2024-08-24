package sh.tech.tourmanagementsystem.service.inter;

import sh.tech.tourmanagementsystem.dto.request.TravelerRequest;
import sh.tech.tourmanagementsystem.dto.response.TravelerResponse;

import java.util.List;

public interface TravelerService {
    TravelerResponse saveUpdateTraveler(TravelerRequest travelerRequest);
    TravelerResponse getTravelerById(Long id);
    List<TravelerResponse> getAllTravelers();
    void deleteTraveler(Long id);

}
