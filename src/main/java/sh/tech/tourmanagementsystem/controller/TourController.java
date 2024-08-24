package sh.tech.tourmanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sh.tech.tourmanagementsystem.dto.request.TourRequest;
import sh.tech.tourmanagementsystem.dto.response.TourResponse;
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
    public ResponseEntity<TourResponse> saveUpdateTour(@RequestBody TourRequest tourRequest){
        return ResponseEntity.status(201).body(tourService.saveUpdateTour(tourRequest));
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public ResponseEntity<Void> deleteTourById(@PathVariable("id") Long id){
        tourService.deleteTourById(id);
        return ResponseEntity.noContent().build();
    }
}
