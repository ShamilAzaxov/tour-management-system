package sh.tech.tourmanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sh.tech.tourmanagementsystem.dao.entity.Destination;
import sh.tech.tourmanagementsystem.dao.repository.DestinationRepository;
import sh.tech.tourmanagementsystem.dao.repository.PassportRepository;
import sh.tech.tourmanagementsystem.dto.request.DestinationRequest;
import sh.tech.tourmanagementsystem.dto.response.DestinationResponse;
import sh.tech.tourmanagementsystem.exception.NotFoundException;
import sh.tech.tourmanagementsystem.mapper.DestinationMapper;
import sh.tech.tourmanagementsystem.service.inter.DestinationService;

import java.util.List;

import static sh.tech.tourmanagementsystem.dto.enums.ExceptionConstant.DESTINATION_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class DestinationServiceImpl implements DestinationService {
    private final DestinationRepository destinationRepository;
    private final DestinationMapper destinationMapper;
    private final PassportRepository passportRepository;

    @Override
    public List<DestinationResponse> getAllDestinations() {
        return destinationRepository.findAll().stream().map(destinationMapper::toDto).toList();
    }

    @Override
    public DestinationResponse getDestinationById(Long id) {
        return destinationMapper.toDto(destinationRepository.findById(id).orElseThrow(
                () -> new NotFoundException(DESTINATION_NOT_FOUND.getCode(), DESTINATION_NOT_FOUND.getMessage())
        ));
    }

    @Override
    public DestinationResponse saveUpdateDestination(DestinationRequest destinationRequest) {
        boolean ifExists = true;
        if (destinationRequest.getId() !=0)
             ifExists = passportRepository.existsById(destinationRequest.getId());
        Destination destination = null;
        if (ifExists) {
            destination = destinationMapper.toEntity(destinationRequest);
            destinationRepository.save(destination);
        }
        else
            throw new NotFoundException(DESTINATION_NOT_FOUND.getCode(), DESTINATION_NOT_FOUND.getMessage());
        return destinationMapper.toDto(destination);
    }

    @Override
    public void deleteDestinationById(Long id) {
        destinationRepository.deleteById(id);
    }
}
