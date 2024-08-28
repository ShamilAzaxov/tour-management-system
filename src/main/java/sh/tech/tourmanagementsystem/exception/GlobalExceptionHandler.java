package sh.tech.tourmanagementsystem.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;
import static sh.tech.tourmanagementsystem.dto.enums.ExceptionConstant.UNEXPECTED_EXCEPTION;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ErrorResponse handle(Exception ex){
      log.error("Exception: ", ex);
      return ErrorResponse.builder().code(UNEXPECTED_EXCEPTION.getCode()).message(UNEXPECTED_EXCEPTION.getMessage()).build();
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse handle(NotFoundException ex){
        log.error("Exception: ", ex);
        return ErrorResponse.builder().code(ex.getCode()).message(ex.getMessage()).build();
    }
    @ExceptionHandler(GuideISBusyException.class)
    @ResponseStatus(SERVICE_UNAVAILABLE)
    public ErrorResponse handle(GuideISBusyException ex){
        log.error("Exception: ", ex);
        return ErrorResponse.builder().code(ex.getCode()).message(ex.getMessage()).build();
    }
}
