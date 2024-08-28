package sh.tech.tourmanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sh.tech.tourmanagementsystem.dto.request.destination.SaveDestinationRequest;
import sh.tech.tourmanagementsystem.dto.request.destination.UpdateDestinationRequest;
import sh.tech.tourmanagementsystem.dto.response.DestinationResponse;
import sh.tech.tourmanagementsystem.service.inter.DestinationService;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("api/destination")
@RequiredArgsConstructor
public class DestinationController {
    private final DestinationService destinationService;

    @GetMapping
    public ResponseEntity<List<DestinationResponse>> getDestinations() {
        return ResponseEntity.ok(destinationService.getAllDestinations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DestinationResponse> getDestinationById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(destinationService.getDestinationById(id));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<DestinationResponse> saveDestination(@RequestBody SaveDestinationRequest saveDestinationRequest) {
        return ResponseEntity.status(201).body(destinationService.saveDestination(saveDestinationRequest));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public ResponseEntity<Void> deleteDestinationById(@PathVariable("id") Long id) {
        destinationService.deleteDestinationById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<DestinationResponse> updateDestination(
            @RequestBody UpdateDestinationRequest updateDestinationRequest) {
        return ResponseEntity.status(201).body(destinationService.updateDestination(updateDestinationRequest));
    }
}
