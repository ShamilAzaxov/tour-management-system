package sh.tech.tourmanagementsystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import sh.tech.tourmanagementsystem.dao.entity.Guide;
import sh.tech.tourmanagementsystem.dto.request.GuideRequest;
import sh.tech.tourmanagementsystem.dto.response.GuideResponse;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface GuideMapper {
    Guide toEntity(GuideRequest request);

    GuideResponse toDto(Guide entity);
}
