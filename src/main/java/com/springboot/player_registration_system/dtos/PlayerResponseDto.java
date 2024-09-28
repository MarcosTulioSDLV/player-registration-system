package com.springboot.player_registration_system.dtos;

import com.springboot.player_registration_system.enums.GroupType;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PlayerResponseDto {

    private String fullName;

    private String email;

    private String phoneNumber;

    private String codename;

    private GroupType groupType;

}
