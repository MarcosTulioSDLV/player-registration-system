package com.springboot.player_registration_system.dtos;

import com.springboot.player_registration_system.enums.GroupType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PlayerRequestDto {

    @NotBlank
    private String fullName;

    @NotBlank
    @Email
    private String email;

    @Pattern(regexp = "^$|^\\(\\d{2}\\) 9\\d{4}-\\d{4}$")
    private String phoneNumber;

    @NotNull
    private GroupType groupType;

}
