package sh.tech.tourmanagementsystem.service.inter;

import sh.tech.tourmanagementsystem.dto.request.DestinationRequest;
import sh.tech.tourmanagementsystem.dto.response.DestinationResponse;

import java.util.List;

public interface DestinationService {
    List<DestinationResponse> getAllDestinations();
    DestinationResponse getDestinationById(Long id);
    DestinationResponse saveUpdateDestination(DestinationRequest destinationRequest);
    void deleteDestinationById(Long id);

}
