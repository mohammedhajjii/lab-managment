package md.hajji.equipmentservice.web;

import md.hajji.equipmentservice.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            ResourceNotFoundException.class,
            UnavailableEquipmentException.class
    })
    public ResponseEntity<String> resourceNotFound(ResourceNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<String> duplicateResource(DuplicateResourceException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(exception.getMessage());
    }




    @ExceptionHandler({
            NullResourceFieldException.class,
            InvalidReservationDateException.class,
            InvalidReservationTimeRangeException.class,
            InvalidImageFormatException.class
    })
    public ResponseEntity<String> nullResourceField(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(exception.getMessage());
    }
}
