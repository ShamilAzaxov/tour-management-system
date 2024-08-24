package sh.tech.tourmanagementsystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import sh.tech.tourmanagementsystem.dao.entity.Tour;
import sh.tech.tourmanagementsystem.dto.request.TourRequest;
import sh.tech.tourmanagementsystem.dto.response.TourResponse;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TourMapper {
    Tour toEntity(TourRequest request);
    TourResponse toDto(Tour entity);
}
