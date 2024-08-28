package sh.tech.tourmanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sh.tech.tourmanagementsystem.dto.request.traveler.SaveTravelerRequest;
import sh.tech.tourmanagementsystem.dto.request.traveler.UpdateTravelerRequest;
import sh.tech.tourmanagementsystem.dto.response.TourResponse;
import sh.tech.tourmanagementsystem.dto.response.TravelerResponse;
import sh.tech.tourmanagementsystem.service.inter.TravelerService;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("api/traveler")
@RequiredArgsConstructor
public class TravelerController {
    private final TravelerService travelerService;

    @GetMapping
    public ResponseEntity<List<TravelerResponse>> getAllTravelers() {
        return ResponseEntity.ok(travelerService.getAllTravelers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TravelerResponse> getTravelerById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(travelerService.getTravelerById(id));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<TravelerResponse> saveUpdateTraveler(@RequestBody SaveTravelerRequest saveTravelerRequest) {
        return ResponseEntity.status(201).body(travelerService.saveTraveler(saveTravelerRequest));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public ResponseEntity<Void> deleteTraveler(@PathVariable("id") Long id) {
        travelerService.deleteTraveler(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<TravelerResponse> updateTraveler(
            @RequestBody UpdateTravelerRequest updateTravelerRequest) {
        return ResponseEntity.status(201).body(travelerService.updateTraveler(updateTravelerRequest));
    }

    @GetMapping("/tours/{id}")
    public ResponseEntity<List<TourResponse>> getToursByTravelerId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(travelerService.getToursByTravelerId(id));
    }

    @PutMapping("/traveler-to-tour")
    @ResponseStatus(NO_CONTENT)
    public ResponseEntity<Void> assignTravelerToTour(@RequestParam Long tourId,
                                                     @RequestParam Long travelerId){
        travelerService.assignTravelerToTour(tourId, travelerId);
        return ResponseEntity.noContent().build();
    }
}
