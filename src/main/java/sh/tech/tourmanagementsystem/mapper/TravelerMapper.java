package sh.tech.tourmanagementsystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import sh.tech.tourmanagementsystem.dao.entity.Traveler;
import sh.tech.tourmanagementsystem.dto.request.TravelerRequest;
import sh.tech.tourmanagementsystem.dto.response.TravelerResponse;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TravelerMapper {
    Traveler toEntity(TravelerRequest request);
    TravelerResponse toDto(Traveler entity);
}
