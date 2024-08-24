package sh.tech.tourmanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sh.tech.tourmanagementsystem.dto.request.GuideRequest;
import sh.tech.tourmanagementsystem.dto.response.GuideResponse;
import sh.tech.tourmanagementsystem.service.inter.GuideService;

import java.util.List;

@RestController
@RequestMapping("api/guide")
@RequiredArgsConstructor
public class GuideController {
    private final GuideService guideService;
    @GetMapping
    public ResponseEntity<List<GuideResponse>> getGuides() {
        return ResponseEntity.ok(guideService.getAllGuide());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuideResponse> getGuideById(@PathVariable("id") Long id){
        return ResponseEntity.ok(guideService.getGuideById(id));
    }

    @PostMapping
    public ResponseEntity<GuideResponse> saveUpdateGuide(@RequestBody GuideRequest guideRequest){
        return ResponseEntity.status(201).body(guideService.saveUpdateGuide(guideRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuideById(@PathVariable("id") Long id){
        guideService.deleteGuideById(id);
        return ResponseEntity.noContent().build();
    }
}
