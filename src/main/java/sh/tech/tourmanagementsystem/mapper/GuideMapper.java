package sh.tech.tourmanagementsystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import sh.tech.tourmanagementsystem.dao.entity.Guide;
import sh.tech.tourmanagementsystem.dto.request.guide.SaveGuideRequest;
import sh.tech.tourmanagementsystem.dto.request.guide.UpdateGuideRequest;
import sh.tech.tourmanagementsystem.dto.response.GuideResponse;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface GuideMapper {
    Guide toEntity(SaveGuideRequest request);
    Guide toEntity(UpdateGuideRequest request);
    @Mapping(source = "passport", target = "passportResponse")
    GuideResponse toDto(Guide entity);
}
