package sh.tech.tourmanagementsystem.exception;

import lombok.Getter;

@Getter
public class GuideISBusyException extends RuntimeException{
    private final String code;

    public GuideISBusyException(String code, String message) {
        super(message);
        this.code = code;
    }
}
