package sh.tech.tourmanagementsystem.service.inter;

import sh.tech.tourmanagementsystem.dto.request.GuideRequest;
import sh.tech.tourmanagementsystem.dto.response.GuideResponse;

import java.util.List;

public interface GuideService {
    GuideResponse saveUpdateGuide(GuideRequest guideRequest);
    List<GuideResponse> getAllGuide();
    GuideResponse getGuideById(Long id);
    void deleteGuideById(Long id);

}
