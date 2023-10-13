package com.hiberus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {VoluntarioNotFoundException.class})
    public ResponseEntity<Object> handleApiRequestException(VoluntarioNotFoundException e){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(
                "El usuario con Id " + e.getId() + " no existe",
                httpStatus
        );

        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {NombreInvalidException.class})
    public ResponseEntity<Object> handleApiRequestException(NombreInvalidException e){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(
                "El nuevo nombre no puede ser ni null ni estar vacio",
                httpStatus
        );

        return new ResponseEntity<>(apiException, httpStatus);
    }

}
