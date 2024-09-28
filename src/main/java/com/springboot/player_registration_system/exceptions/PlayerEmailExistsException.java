package com.springboot.player_registration_system.exceptions;

public class PlayerEmailExistsException extends RuntimeException{

    public PlayerEmailExistsException(){
    }

    public PlayerEmailExistsException(String message){
        super(message);
    }

}
