package com.springboot.player_registration_system.controllers;

import com.springboot.player_registration_system.dtos.PlayerRequestDto;
import com.springboot.player_registration_system.dtos.PlayerResponseDto;
import com.springboot.player_registration_system.dtos.UpdatePlayerRequestDto;
import com.springboot.player_registration_system.enums.GroupType;
import com.springboot.player_registration_system.services.PlayerService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }


    @GetMapping(value = "/players")
    public ResponseEntity<Page<PlayerResponseDto>> getAllPlayers(@PageableDefault(size = 10) Pageable pageable){
        return ResponseEntity.ok(playerService.getAllPlayers(pageable));
    }

    @GetMapping(value = "/players-by-id/{id}")
    public ResponseEntity<PlayerResponseDto> getPlayerById(@PathVariable Long id){
        return ResponseEntity.ok(playerService.getPlayerById(id));
    }

    @GetMapping(value = "/players-by-group-type/{groupType}")
    public ResponseEntity<Page<PlayerResponseDto>> getPlayersByGroupType(@PathVariable GroupType groupType,
                                                                         @PageableDefault(size = 10) Pageable pageable){
        return ResponseEntity.ok(playerService.getPlayersByGroupType(groupType,pageable));
    }

    //Note: This return a page because the same codename could be duplicated in different grouptypes (but a codename can not be duplicated in the same grouptype).
    @GetMapping(value = "/players-by-codename/{codename}")
    public ResponseEntity<Page<PlayerResponseDto>> getPlayersByCodename(@PathVariable String codename,
                                                                        @PageableDefault(size = 10) Pageable pageable){
        return ResponseEntity.ok(playerService.getPlayersByCodename(codename,pageable));
    }

    @PostMapping(value = "/players")
    public ResponseEntity<PlayerResponseDto> addPlayer(@RequestBody @Valid PlayerRequestDto playerRequestDto){
        return new ResponseEntity<>(playerService.addPlayer(playerRequestDto),HttpStatus.CREATED);
    }

    @PutMapping(value = "/players/{id}")
    public ResponseEntity<PlayerResponseDto> updatePlayer(@RequestBody @Valid UpdatePlayerRequestDto updatePlayerRequestDto,
                                                          @PathVariable Long id){
        return ResponseEntity.ok(playerService.updatePlayer(updatePlayerRequestDto,id));
    }

    //METHOD NOT REQUIRED
    /*@DeleteMapping(value = "/players/{id}")
    public ResponseEntity<String> removePlayer(@PathVariable Long id){
        playerService.removePlayer(id);
        return ResponseEntity.ok("Player Removed successfully!");
    }*/

}
