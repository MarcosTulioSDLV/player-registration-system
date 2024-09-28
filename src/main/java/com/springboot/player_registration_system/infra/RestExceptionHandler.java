package com.springboot.player_registration_system.infra;


import com.springboot.player_registration_system.exceptions.AvengersCodenameNotAvailable;
import com.springboot.player_registration_system.exceptions.JusticeLeagueCodenameNotAvailable;
import com.springboot.player_registration_system.exceptions.PlayerEmailExistsException;
import com.springboot.player_registration_system.exceptions.PlayerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class RestExceptionHandler {

    //For CodenameHandler class

    @ExceptionHandler(AvengersCodenameNotAvailable.class)
    public ResponseEntity<Object> handleAvengersCodenameNotAvailable(AvengersCodenameNotAvailable e){
        HttpStatus httpStatus= HttpStatus.BAD_REQUEST;
        Map<String,Object> errorResponse= new LinkedHashMap<>();
        errorResponse.put("error",e.getMessage());
        errorResponse.put("status",httpStatus.value());
        return new ResponseEntity<>(errorResponse,httpStatus);
    }

    @ExceptionHandler(JusticeLeagueCodenameNotAvailable.class)
    public ResponseEntity<Object> handleJusticeLeagueCodenameNotAvailable(JusticeLeagueCodenameNotAvailable e){
        HttpStatus httpStatus= HttpStatus.BAD_REQUEST;
        Map<String,Object> errorResponse= new LinkedHashMap<>();
        errorResponse.put("error",e.getMessage());
        errorResponse.put("status",httpStatus.value());
        return new ResponseEntity<>(errorResponse,httpStatus);
    }

    //-----

    //For Player class

    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<Object> handlePlayerNotFoundException(PlayerNotFoundException e){
        HttpStatus httpStatus= HttpStatus.NOT_FOUND;
        Map<String,Object> errorResponse= new LinkedHashMap<>();
        errorResponse.put("error",e.getMessage());
        errorResponse.put("status",httpStatus.value());
        return new ResponseEntity<>(errorResponse,httpStatus);
    }

    @ExceptionHandler(PlayerEmailExistsException.class)
    public ResponseEntity<Object> handlePlayerEmailExistsException(PlayerEmailExistsException e){
        HttpStatus httpStatus= HttpStatus.BAD_REQUEST;
        Map<String,Object> errorResponse= new LinkedHashMap<>();
        errorResponse.put("error",e.getMessage());
        errorResponse.put("status",httpStatus.value());
        return new ResponseEntity<>(errorResponse,httpStatus);
    }
    //-----

}
