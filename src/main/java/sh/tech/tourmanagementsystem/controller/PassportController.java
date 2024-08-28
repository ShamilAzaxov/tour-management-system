package sh.tech.tourmanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sh.tech.tourmanagementsystem.dto.request.passport.UpdatePassportRequest;
import sh.tech.tourmanagementsystem.dto.response.passport.PassportResponse;
import sh.tech.tourmanagementsystem.service.inter.PassportService;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

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
    @ResponseStatus(CREATED)
    public ResponseEntity<PassportResponse> updatePassport(@RequestBody UpdatePassportRequest updatePassportRequest){
        return ResponseEntity.status(201).body(passportService.updatePassport(updatePassportRequest));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public ResponseEntity<Void> deletePassport(@PathVariable("id") Long id){
        passportService.deletePassportById(id);
        return ResponseEntity.noContent().build();
    }
}