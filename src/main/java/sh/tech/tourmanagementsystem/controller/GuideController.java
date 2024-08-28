package sh.tech.tourmanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sh.tech.tourmanagementsystem.dto.enums.GuideStatus;
import sh.tech.tourmanagementsystem.dto.request.guide.SaveGuideRequest;
import sh.tech.tourmanagementsystem.dto.request.guide.UpdateGuideRequest;
import sh.tech.tourmanagementsystem.dto.response.GuideResponse;
import sh.tech.tourmanagementsystem.service.inter.GuideService;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

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
    public ResponseEntity<GuideResponse> saveGuide(@RequestBody SaveGuideRequest saveGuideRequest){
        return ResponseEntity.status(201).body(guideService.saveGuide(saveGuideRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuideById(@PathVariable("id") Long id){
        guideService.deleteGuideById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<GuideResponse> updateGuide(@RequestBody UpdateGuideRequest updateGuideRequest){
        return ResponseEntity.status(201).body(guideService.updateGuide(updateGuideRequest));
    }
    @PutMapping("/update-status")
    @ResponseStatus(CREATED)
    public ResponseEntity<GuideResponse> updateGuideStatus(@RequestParam GuideStatus status,
                                                           @RequestParam Long id){
        return ResponseEntity.status(201).body(guideService.updateGuideStatus(status, id));
    }

    @GetMapping("/free-guides")
    public ResponseEntity<List<GuideResponse>> getFreeGuides(){
        return ResponseEntity.ok(guideService.getFreeGuides());
    }
}
