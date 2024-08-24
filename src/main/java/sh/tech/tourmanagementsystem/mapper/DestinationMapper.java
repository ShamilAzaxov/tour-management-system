package sh.tech.tourmanagementsystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import sh.tech.tourmanagementsystem.dao.entity.Destination;
import sh.tech.tourmanagementsystem.dto.request.DestinationRequest;
import sh.tech.tourmanagementsystem.dto.response.DestinationResponse;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface DestinationMapper {
    Destination toEntity(DestinationRequest request);
    DestinationResponse toDto(Destination entity);
}
