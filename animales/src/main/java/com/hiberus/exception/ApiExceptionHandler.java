package com.hiberus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {PizzaNotFoundException.class})
    public ResponseEntity<Object> handleApiRequestException(PizzaNotFoundException e){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(
                "La pizza con Id " + e.getId() + " no existe",
                httpStatus
        );

        return new ResponseEntity<>(apiException, httpStatus);
    }

}
