package sh.tech.tourmanagementsystem.service.inter;

import sh.tech.tourmanagementsystem.dto.request.PassportRequest;
import sh.tech.tourmanagementsystem.dto.response.PassportResponse;

import java.util.List;

public interface PassportService {
    PassportResponse updatePassport(PassportRequest passportRequest);
    PassportResponse getPassportByNumber(String passportNumber);
    void deletePassportById(Long id);
    PassportResponse getPassportById(Long id);
    List<PassportResponse> getAllPassports();

}
