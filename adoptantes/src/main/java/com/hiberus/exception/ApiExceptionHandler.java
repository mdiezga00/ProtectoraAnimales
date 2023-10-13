package com.hiberus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {AdoptanteNotFoundException.class})
    public ResponseEntity<Object> handleApiRequestException(AdoptanteNotFoundException e){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(
                "El usuario con Id " + e.getId() + " no existe",
                httpStatus
        );

        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {AnimalNotFoundException.class})
    public ResponseEntity<Object> handleApiRequestException(AnimalNotFoundException e){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(
                "El animal con Id " + e.getId() + " no existe",
                httpStatus
        );

        return new ResponseEntity<>(apiException, httpStatus);
    }

}
