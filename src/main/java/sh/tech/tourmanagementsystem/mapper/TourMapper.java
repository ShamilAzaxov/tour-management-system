package sh.tech.tourmanagementsystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import sh.tech.tourmanagementsystem.dao.entity.Tour;
import sh.tech.tourmanagementsystem.dto.request.tour.SaveTourRequest;
import sh.tech.tourmanagementsystem.dto.request.tour.UpdateTourRequest;
import sh.tech.tourmanagementsystem.dto.response.TourResponse;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TourMapper {
    Tour toEntity(SaveTourRequest request);
    Tour toEntity(UpdateTourRequest request);
    TourResponse toDto(Tour entity);
}
