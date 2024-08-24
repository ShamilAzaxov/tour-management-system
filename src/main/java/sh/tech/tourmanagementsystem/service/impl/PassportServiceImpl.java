package sh.tech.tourmanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sh.tech.tourmanagementsystem.dao.entity.Passport;
import sh.tech.tourmanagementsystem.dao.repository.PassportRepository;
import sh.tech.tourmanagementsystem.dto.request.PassportRequest;
import sh.tech.tourmanagementsystem.dto.response.PassportResponse;
import sh.tech.tourmanagementsystem.exception.NotFoundException;
import sh.tech.tourmanagementsystem.mapper.PassportMapper;
import sh.tech.tourmanagementsystem.service.inter.PassportService;

import java.util.List;

import static sh.tech.tourmanagementsystem.dto.enums.ExceptionConstant.PASSPORT_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class PassportServiceImpl implements PassportService {
    private final PassportRepository passportRepository;
    private final PassportMapper passportMapper;
    @Override
    public PassportResponse updatePassport(PassportRequest passportRequest) {
        if (!fetchPassportIfExists(passportRequest.getId()))
            throw new NotFoundException(PASSPORT_NOT_FOUND.getCode(),
                    PASSPORT_NOT_FOUND.getMessage() + " by id: " + passportRequest.getId());
        Passport passport = passportMapper.toEntity(passportRequest);
        passportRepository.save(passport);
        return passportMapper.toDto(passport);
    }

    @Override
    public PassportResponse getPassportByNumber(String passportNumber) {
        return passportMapper.toDto(passportRepository.findPassportByPassportNumber(passportNumber)
                .orElseThrow(() ->new NotFoundException(PASSPORT_NOT_FOUND.getCode(),
                        PASSPORT_NOT_FOUND.getMessage()+ " by passport number " + passportNumber)));
    }

    @Override
    public void deletePassportById(Long id) {
        passportRepository.deleteById(id);

    }

    @Override
    public PassportResponse getPassportById(Long id) {
        return passportMapper.toDto(passportRepository.findById(id).orElseThrow(() ->
                new NotFoundException(PASSPORT_NOT_FOUND.getCode(),
                        PASSPORT_NOT_FOUND.getMessage() + " by id: " + id)));
    }

    @Override
    public List<PassportResponse> getAllPassports() {
        return passportRepository.findAll().stream().map(passportMapper::toDto).toList();
    }

    private boolean fetchPassportIfExists(Long id){
        return passportRepository.existsById(id);
    }
}
