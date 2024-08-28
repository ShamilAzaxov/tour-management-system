package sh.tech.tourmanagementsystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import sh.tech.tourmanagementsystem.dao.entity.Destination;
import sh.tech.tourmanagementsystem.dto.request.destination.SaveDestinationRequest;
import sh.tech.tourmanagementsystem.dto.request.destination.UpdateDestinationRequest;
import sh.tech.tourmanagementsystem.dto.response.DestinationResponse;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface DestinationMapper {
    Destination toEntity(SaveDestinationRequest request);
    Destination toEntity(UpdateDestinationRequest request);
    DestinationResponse toDto(Destination entity);
}
