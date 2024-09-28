package com.springboot.player_registration_system.exceptions;

public class PlayerNotFoundException extends RuntimeException{

    public PlayerNotFoundException(){
    }

    public PlayerNotFoundException(String message){
        super(message);
    }

}
