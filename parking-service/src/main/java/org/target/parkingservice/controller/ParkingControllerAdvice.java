package org.target.parkingservice.controller;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.target.parkingservice.exception.InvalidSSRException;
import org.target.parkingservice.exception.ParkingFullException;

import java.util.Optional;

@ControllerAdvice
@RequestMapping(produces = "application/vnd.error+json")
public class ParkingControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ParkingFullException.class)
    public ResponseEntity<VndErrors> notFoundException(final ParkingFullException e) {
        return error(e, HttpStatus.NOT_FOUND, e.getMessage());
    }
    @ExceptionHandler(InvalidSSRException.class)
    public ResponseEntity<VndErrors> invalidSSRException(final InvalidSSRException e) {
        return error(e, HttpStatus.NOT_FOUND, e.getMessage());
    }

    private ResponseEntity<VndErrors> error(
            final Exception exception, final HttpStatus httpStatus, final String logRef) {
        final String message =
                Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        return new ResponseEntity<>(new VndErrors(logRef, message), httpStatus);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<VndErrors> assertionException(final IllegalArgumentException e) {
        return error(e, HttpStatus.NOT_FOUND, e.getLocalizedMessage());
    }
}