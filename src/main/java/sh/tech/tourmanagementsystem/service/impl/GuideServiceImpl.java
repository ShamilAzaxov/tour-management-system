package sh.tech.tourmanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sh.tech.tourmanagementsystem.dao.entity.Guide;
import sh.tech.tourmanagementsystem.dao.entity.Passport;
import sh.tech.tourmanagementsystem.dao.repository.GuideRepository;
import sh.tech.tourmanagementsystem.dao.repository.PassportRepository;
import sh.tech.tourmanagementsystem.dto.request.GuideRequest;
import sh.tech.tourmanagementsystem.dto.response.GuideResponse;
import sh.tech.tourmanagementsystem.exception.NotFoundException;
import sh.tech.tourmanagementsystem.mapper.GuideMapper;
import sh.tech.tourmanagementsystem.mapper.PassportMapper;
import sh.tech.tourmanagementsystem.service.inter.GuideService;

import java.util.List;

import static sh.tech.tourmanagementsystem.dto.enums.ExceptionConstant.GUIDE_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class GuideServiceImpl implements GuideService {
    private final GuideRepository guideRepository;
    private final GuideMapper guideMapper;
    private final PassportMapper passportMapper;
    private final PassportRepository passportRepository;

    @Transactional
    @Override
    public GuideResponse saveUpdateGuide(GuideRequest guideRequest) {
        Guide guide = guideMapper.toEntity(guideRequest);
        Passport passport = passportMapper.toEntity(guideRequest.getPassport());
        passport.setGuide(guide);
        passport.setId(guide.getId());
        guide.setPassport(passport);
        guideRepository.save(guide);
        return guideMapper.toDto(guide);
    }

    @Override
    public List<GuideResponse> getAllGuide() {
        return guideRepository.findAll().stream().map(guideMapper::toDto).toList();
    }

    @Override
    public GuideResponse getGuideById(Long id) {
        return guideMapper.toDto(guideRepository.findById(id).orElseThrow(() ->
                new NotFoundException(GUIDE_NOT_FOUND.getCode(), GUIDE_NOT_FOUND.getMessage())));
    }

    @Override
    public void deleteGuideById(Long id) {
        guideRepository.deleteById(id);
    }
}