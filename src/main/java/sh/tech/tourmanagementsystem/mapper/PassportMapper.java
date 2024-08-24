package sh.tech.tourmanagementsystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import sh.tech.tourmanagementsystem.dao.entity.Passport;
import sh.tech.tourmanagementsystem.dto.request.PassportRequest;
import sh.tech.tourmanagementsystem.dto.response.PassportResponse;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PassportMapper {
    Passport toEntity(PassportRequest request);
    PassportResponse toDto(Passport entity);
}
