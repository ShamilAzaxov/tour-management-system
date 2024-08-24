package sh.tech.tourmanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sh.tech.tourmanagementsystem.dto.request.PassportRequest;
import sh.tech.tourmanagementsystem.dto.response.PassportResponse;
import sh.tech.tourmanagementsystem.service.inter.PassportService;

import java.util.List;

@RestController
@RequestMapping("api/passport")
@RequiredArgsConstructor
public class PassportController {
    private final PassportService passportService;

    @GetMapping
    public ResponseEntity<List<PassportResponse>> getPassports(){
        return ResponseEntity.ok(passportService.getAllPassports());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PassportResponse> getPassportById(@PathVariable("id") Long id){
        return ResponseEntity.ok(passportService.getPassportById(id));
    }
    @PutMapping
    public ResponseEntity<PassportResponse> updatePassport(@RequestBody PassportRequest passportRequest){
        return ResponseEntity.status(201).body(passportService.updatePassport(passportRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePassport(@PathVariable("id") Long id){
        passportService.deletePassportById(id);
        return ResponseEntity.noContent().build();
    }
}