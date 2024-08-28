package sh.tech.tourmanagementsystem.service.inter;

import sh.tech.tourmanagementsystem.dto.enums.GuideStatus;
import sh.tech.tourmanagementsystem.dto.request.guide.SaveGuideRequest;
import sh.tech.tourmanagementsystem.dto.request.guide.UpdateGuideRequest;
import sh.tech.tourmanagementsystem.dto.response.GuideResponse;

import java.util.List;

public interface GuideService {
    GuideResponse saveGuide(SaveGuideRequest saveGuideRequest);
    List<GuideResponse> getAllGuide();
    GuideResponse getGuideById(Long id);
    void deleteGuideById(Long id);
    GuideResponse updateGuide(UpdateGuideRequest updateGuideRequest);
    GuideResponse updateGuideStatus(GuideStatus status, Long id);
    void updateGuidesStatus();
    List<GuideResponse> getFreeGuides();
}