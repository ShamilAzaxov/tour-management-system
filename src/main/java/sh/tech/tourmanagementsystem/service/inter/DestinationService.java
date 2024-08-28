package sh.tech.tourmanagementsystem.service.inter;

import sh.tech.tourmanagementsystem.dto.request.destination.SaveDestinationRequest;
import sh.tech.tourmanagementsystem.dto.request.destination.UpdateDestinationRequest;
import sh.tech.tourmanagementsystem.dto.response.DestinationResponse;

import java.util.List;

public interface DestinationService {
    List<DestinationResponse> getAllDestinations();
    DestinationResponse getDestinationById(Long id);
    DestinationResponse saveDestination(SaveDestinationRequest saveDestinationRequest);
    void deleteDestinationById(Long id);
    DestinationResponse updateDestination(UpdateDestinationRequest updateDestinationRequest);

}
