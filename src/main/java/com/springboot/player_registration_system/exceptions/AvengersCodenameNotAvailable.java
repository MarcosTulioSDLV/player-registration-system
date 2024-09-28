package com.springboot.player_registration_system.exceptions;

public class AvengersCodenameNotAvailable extends RuntimeException{

    public AvengersCodenameNotAvailable(){
    }

    public AvengersCodenameNotAvailable(String message){
        super(message);
    }

}
