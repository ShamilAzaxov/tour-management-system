package sh.tech.tourmanagementsystem.service.inter;

import sh.tech.tourmanagementsystem.dto.request.passport.UpdatePassportRequest;
import sh.tech.tourmanagementsystem.dto.response.passport.PassportResponse;

import java.util.List;

public interface PassportService {
    PassportResponse updatePassport(UpdatePassportRequest updatePassportRequest);
    PassportResponse getPassportByNumber(String passportNumber);
    void deletePassportById(Long id);
    PassportResponse getPassportById(Long id);
    List<PassportResponse> getAllPassports();

}
