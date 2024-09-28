package com.springboot.player_registration_system.dtos;

import com.springboot.player_registration_system.enums.GroupType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdatePlayerRequestDto {

    @NotBlank
    private String fullName;

    @NotBlank
    @Email
    private String email;

    @Pattern(regexp = "^$|^\\(\\d{2}\\) 9\\d{4}-\\d{4}$")
    private String phoneNumber;

}
