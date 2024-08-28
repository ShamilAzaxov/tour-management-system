package sh.tech.tourmanagementsystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import sh.tech.tourmanagementsystem.dao.entity.Traveler;
import sh.tech.tourmanagementsystem.dto.request.traveler.SaveTravelerRequest;
import sh.tech.tourmanagementsystem.dto.request.traveler.UpdateTravelerRequest;
import sh.tech.tourmanagementsystem.dto.response.TravelerResponse;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TravelerMapper {
    Traveler toEntity(SaveTravelerRequest request);
    Traveler toEntity(UpdateTravelerRequest request);
    TravelerResponse toDto(Traveler entity);
}
