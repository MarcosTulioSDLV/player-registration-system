package com.springboot.player_registration_system.services;

import com.springboot.player_registration_system.dtos.PlayerRequestDto;
import com.springboot.player_registration_system.dtos.PlayerResponseDto;
import com.springboot.player_registration_system.dtos.UpdatePlayerRequestDto;
import com.springboot.player_registration_system.enums.GroupType;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface PlayerService {

    Page<PlayerResponseDto> getAllPlayers(Pageable pageable);

    PlayerResponseDto getPlayerById(Long id);

    Page<PlayerResponseDto> getPlayersByGroupType(GroupType groupType,Pageable pageable);

    Page<PlayerResponseDto> getPlayersByCodename(String codename,Pageable pageable);

    @Transactional
    PlayerResponseDto addPlayer(PlayerRequestDto playerRequestDto);

    @Transactional
    PlayerResponseDto updatePlayer(UpdatePlayerRequestDto updatePlayerRequestDto,Long id);

    /*@Transactional
    void removePlayer(Long id);*/
}
