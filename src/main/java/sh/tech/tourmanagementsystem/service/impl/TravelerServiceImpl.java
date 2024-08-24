package sh.tech.tourmanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sh.tech.tourmanagementsystem.dao.entity.Traveler;
import sh.tech.tourmanagementsystem.dao.repository.TravelerRepository;
import sh.tech.tourmanagementsystem.dto.request.TravelerRequest;
import sh.tech.tourmanagementsystem.dto.response.TravelerResponse;
import sh.tech.tourmanagementsystem.exception.NotFoundException;
import sh.tech.tourmanagementsystem.mapper.TravelerMapper;
import sh.tech.tourmanagementsystem.service.inter.TravelerService;

import java.util.List;

import static sh.tech.tourmanagementsystem.dto.enums.ExceptionConstant.TRAVELER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class TravelerServiceImpl implements TravelerService {
    private final TravelerRepository travelerRepository;
    private final TravelerMapper travelerMapper;

    @Override
    public TravelerResponse saveUpdateTraveler(TravelerRequest travelerRequest) {
        boolean ifExists = true;
        if (travelerRequest.getId() != 0)
            ifExists = travelerRepository.existsById(travelerRequest.getId());
        Traveler traveler = null;
        if (ifExists) {
            traveler = travelerMapper.toEntity(travelerRequest);
            travelerRepository.save(traveler);
        }
        else
            throw new NotFoundException(TRAVELER_NOT_FOUND.getCode(), TRAVELER_NOT_FOUND.getMessage());
        return travelerMapper.toDto(traveler);
    }

    @Override
    public TravelerResponse getTravelerById(Long id) {
        return travelerMapper.toDto(travelerRepository.findById(id).orElseThrow(
                () -> new NotFoundException(TRAVELER_NOT_FOUND.getCode(), TRAVELER_NOT_FOUND.getMessage())
        ));
    }

    @Override
    public List<TravelerResponse> getAllTravelers() {
        return travelerRepository.findAll().stream().map(travelerMapper::toDto).toList();
    }

    @Override
    public void deleteTraveler(Long id) {
        travelerRepository.deleteById(id);
    }
}
