package com.springboot.player_registration_system.exceptions;

public class JusticeLeagueCodenameNotAvailable extends RuntimeException{

    public JusticeLeagueCodenameNotAvailable(){
    }

    public JusticeLeagueCodenameNotAvailable(String message){
        super(message);
    }
}
