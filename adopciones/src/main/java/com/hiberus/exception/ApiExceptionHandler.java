package com.hiberus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {SolicitudNotFoundException.class})
    public ResponseEntity<Object> handleApiRequestException(SolicitudNotFoundException e){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(
                "La soilicitud con Id " + e.getId() + " no existe",
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

    @ExceptionHandler(value = {VoluntarioNotFoundException.class})
    public ResponseEntity<Object> handleApiRequestException(VoluntarioNotFoundException e){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(
                "El voluntario con Id " + e.getId() + " no existe",
                httpStatus
        );

        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {AdoptanteNotFoundException.class})
    public ResponseEntity<Object> handleApiRequestException(AdoptanteNotFoundException e){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(
                "El adoptante con Id " + e.getId() + " no existe",
                httpStatus
        );

        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {SolicitudYaGestionadaException.class})
    public ResponseEntity<Object> handleApiRequestException(SolicitudYaGestionadaException e){
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        ApiException apiException = new ApiException(
                "La solicitud con Id " + e.getId() + " ya ha sido aprobada o rechazada",
                httpStatus
        );

        return new ResponseEntity<>(apiException, httpStatus);
    }

}
