package sh.tech.tourmanagementsystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import sh.tech.tourmanagementsystem.dao.entity.Passport;
import sh.tech.tourmanagementsystem.dto.request.passport.SavePassportRequest;
import sh.tech.tourmanagementsystem.dto.request.passport.UpdatePassportRequest;
import sh.tech.tourmanagementsystem.dto.response.passport.PassportResponse;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PassportMapper {
    Passport toEntity(UpdatePassportRequest request);
    Passport toEntity(SavePassportRequest request);
    PassportResponse toDto(Passport entity);
}
