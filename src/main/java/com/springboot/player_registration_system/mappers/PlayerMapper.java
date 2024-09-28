package com.springboot.player_registration_system.mappers;

import com.springboot.player_registration_system.dtos.PlayerRequestDto;
import com.springboot.player_registration_system.dtos.PlayerResponseDto;
import com.springboot.player_registration_system.dtos.UpdatePlayerRequestDto;
import com.springboot.player_registration_system.models.Player;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public PlayerMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Player toPlayer(PlayerRequestDto playerRequestDto){
        return modelMapper.map(playerRequestDto,Player.class);
    }

    public Player toPlayer(UpdatePlayerRequestDto updatePlayerRequestDto){
        return modelMapper.map(updatePlayerRequestDto,Player.class);
    }

    public PlayerResponseDto toPlayerResponseDto(Player player){
        return modelMapper.map(player,PlayerResponseDto.class);
    }

}
