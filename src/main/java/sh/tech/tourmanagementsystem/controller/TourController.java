package sh.tech.tourmanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sh.tech.tourmanagementsystem.dto.request.tour.SaveTourRequest;
import sh.tech.tourmanagementsystem.dto.request.tour.UpdateTourRequest;
import sh.tech.tourmanagementsystem.dto.response.DestinationResponse;
import sh.tech.tourmanagementsystem.dto.response.GuideResponse;
import sh.tech.tourmanagementsystem.dto.response.TourResponse;
import sh.tech.tourmanagementsystem.dto.response.TravelerResponse;
import sh.tech.tourmanagementsystem.service.inter.TourService;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("api/tour")
@RequiredArgsConstructor
public class TourController {
    private final TourService tourService;
    @GetMapping
    public ResponseEntity<List<TourResponse>> getAllTours() {
        return ResponseEntity.ok(tourService.getAllTours());
    }
    @GetMapping("/{id}")
    public ResponseEntity<TourResponse> getTourById(@PathVariable("id") Long id){
        return ResponseEntity.ok(tourService.getTourById(id));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<TourResponse> saveUpdateTour(@RequestBody SaveTourRequest saveTourRequest){
        return ResponseEntity.status(201).body(tourService.saveTour(saveTourRequest));
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public ResponseEntity<Void> deleteTourById(@PathVariable("id") Long id){
        tourService.deleteTourById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<TourResponse> updateTour(@RequestBody UpdateTourRequest updateTourRequest){
        return ResponseEntity.status(201).body(tourService.updateTour(updateTourRequest));
    }

    @PutMapping("/assign-guide")
    @ResponseStatus(NO_CONTENT)
    public ResponseEntity<Void> assignGuideToTour(@RequestParam Long guideId, @RequestParam Long tourId){
        tourService.assignGuideToTour(guideId, tourId);
        return  ResponseEntity.noContent().build();
    }

    @GetMapping("/destinations/{id}")
    public ResponseEntity<List<DestinationResponse>> getDestinationsByTourId(@PathVariable("id") Long tourId){
        return ResponseEntity.ok(tourService.getAllDestinationsByTourId(tourId));
    }

    @GetMapping("/travelers/{id}")
    public ResponseEntity<List<TravelerResponse>> getTravelersByTourId(@PathVariable("id") Long tourId){
        return ResponseEntity.ok(tourService.getTravelersByTourId(tourId));
    }

    @GetMapping("/guides/{id}")
    public ResponseEntity<List<GuideResponse>> getGuidesByTourId(@PathVariable("id") Long id){
        return ResponseEntity.ok(tourService.getGuidesByTourId(id));
    }
}
