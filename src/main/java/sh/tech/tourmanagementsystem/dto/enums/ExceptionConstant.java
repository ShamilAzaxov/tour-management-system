package sh.tech.tourmanagementsystem.dto.enums;

import lombok.Getter;

@Getter
public enum ExceptionConstant {
    UNEXPECTED_EXCEPTION("INTERNAL SERVER ERROR", "Occurred unexpected exception"),
    PASSPORT_NOT_FOUND("NOT FOUND ERROR", "Passport not found"),
    GUIDE_NOT_FOUND("NOT FOUND ERROR", "Guide not found"),
    DESTINATION_NOT_FOUND("NOT FOUND ERROR", "Destination not found"),
    TRAVELER_NOT_FOUND("NOT FOUND ERROR", "Traveler not found"),
    TOUR_NOT_FOUND("NOT FOUND ERROR", "Tour not found");

    private final String message;
    private final String code;

    ExceptionConstant(String message, String code) {
        this.message = message;
        this.code = code;
    }
}
