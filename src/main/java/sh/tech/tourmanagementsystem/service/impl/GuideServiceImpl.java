package sh.tech.tourmanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sh.tech.tourmanagementsystem.dao.entity.Guide;
import sh.tech.tourmanagementsystem.dao.entity.Passport;
import sh.tech.tourmanagementsystem.dao.repository.GuideRepository;
import sh.tech.tourmanagementsystem.dao.repository.PassportRepository;
import sh.tech.tourmanagementsystem.dto.enums.GuideStatus;
import sh.tech.tourmanagementsystem.dto.request.guide.SaveGuideRequest;
import sh.tech.tourmanagementsystem.dto.request.guide.UpdateGuideRequest;
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
    public GuideResponse saveGuide(SaveGuideRequest saveGuideRequest) {
        Guide guide = guideMapper.toEntity(saveGuideRequest);
        Passport passport = passportMapper.toEntity(saveGuideRequest.getPassport());
        passport.setGuide(guide);
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
        fetchIfGuideExists(id);
        guideRepository.deleteById(id);
    }

    @Override
    public GuideResponse updateGuide(UpdateGuideRequest updateGuideRequest) {
        fetchIfGuideExists(updateGuideRequest.getId());
        Guide guide = guideMapper.toEntity(updateGuideRequest);
        return guideMapper.toDto(guideRepository.save(guide));
    }

    @Override
    public GuideResponse updateGuideStatus(GuideStatus status, Long id) {
        Guide guide = guideRepository.findById(id).orElseThrow(
                () -> new NotFoundException(GUIDE_NOT_FOUND.getCode(), GUIDE_NOT_FOUND.getMessage()));
        guide.setStatus(status);
        return guideMapper.toDto(guideRepository.save(guide));
    }

    @Override
    @Scheduled(cron = "0 0 0/12 * * *")
    @Transactional
    public void updateGuidesStatus() {
        guideRepository.updateGuidesStatus();
    }


    @Override
    public List<GuideResponse> getFreeGuides() {
        return guideRepository.findByStatus(GuideStatus.FREE).stream().map(guideMapper::toDto).toList();
    }

    private void fetchIfGuideExists(Long id) {
        if (!guideRepository.existsById(id))
            throw new NotFoundException(GUIDE_NOT_FOUND.getCode(),
                    GUIDE_NOT_FOUND.getMessage() + " by id: " + id);
    }
}