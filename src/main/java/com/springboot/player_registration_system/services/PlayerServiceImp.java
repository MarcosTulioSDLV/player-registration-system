package com.springboot.player_registration_system.services;

import com.springboot.player_registration_system.dtos.PlayerRequestDto;
import com.springboot.player_registration_system.dtos.PlayerResponseDto;
import com.springboot.player_registration_system.dtos.UpdatePlayerRequestDto;
import com.springboot.player_registration_system.enums.GroupType;
import com.springboot.player_registration_system.exceptions.PlayerEmailExistsException;
import com.springboot.player_registration_system.exceptions.PlayerNotFoundException;
import com.springboot.player_registration_system.infra.CodenameHandler;
import com.springboot.player_registration_system.mappers.PlayerMapper;
import com.springboot.player_registration_system.models.Player;
import com.springboot.player_registration_system.repositories.PlayerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImp implements PlayerService{

    private final PlayerRepository playerRepository;

    private final PlayerMapper playerMapper;

    private final CodenameHandler codenameHandler;

    @Autowired
    public PlayerServiceImp(PlayerRepository playerRepository, PlayerMapper playerMapper, CodenameHandler codenameHandler) {
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
        this.codenameHandler = codenameHandler;
    }

    @Override
    public Page<PlayerResponseDto> getAllPlayers(Pageable pageable) {
        return playerRepository.findAll(pageable).map(playerMapper::toPlayerResponseDto);
    }

    @Override
    public PlayerResponseDto getPlayerById(Long id) {
        Player player= findPlayerById(id);
        return playerMapper.toPlayerResponseDto(player);
    }

    private Player findPlayerById(Long id) {
        return playerRepository.findById(id).orElseThrow(() ->new PlayerNotFoundException("Player with id: " + id + " not found!"));
    }

    @Override
    public Page<PlayerResponseDto> getPlayersByGroupType(GroupType groupType,Pageable pageable) {
        return playerRepository.findAllByGroupType(groupType,pageable).map(playerMapper::toPlayerResponseDto);
    }

    @Override
    public Page<PlayerResponseDto> getPlayersByCodename(String codename,Pageable pageable) {
        return playerRepository.findAllByCodenameIgnoreCase(codename,pageable).map(playerMapper::toPlayerResponseDto);
    }

    @Override
    @Transactional
    public PlayerResponseDto addPlayer(PlayerRequestDto playerRequestDto) {
        Player player= playerMapper.toPlayer(playerRequestDto);

        handleEmptyFields(player);
        validateUniqueFields(player);

        String codename= codenameHandler.getCodenameFrom(player.getGroupType());
        player.setCodename(codename);

        return playerMapper.toPlayerResponseDto(playerRepository.save(player));
    }

    //Note: Convert/Normalize Empty Strings To Null
    private void handleEmptyFields(Player player) {
        if(player.getPhoneNumber()==null || player.getPhoneNumber().isBlank()){
            player.setPhoneNumber(null);
        }
    }

    private void validateUniqueFields(Player player) {
        if(playerRepository.existsByEmailIgnoreCase(player.getEmail())){
            throw new PlayerEmailExistsException("Player with email: "+ player.getEmail()+" already exists!");
        }
    }

    @Override
    @Transactional
    public PlayerResponseDto updatePlayer(UpdatePlayerRequestDto updatePlayerRequestDto,Long id) {
        Player player= playerMapper.toPlayer(updatePlayerRequestDto);
        player.setId(id);

        Player recoveredPlayer= findPlayerById(id);

        handleEmptyFields(player);
        validateFieldsUpdateConflict(player,recoveredPlayer);

        BeanUtils.copyProperties(player,recoveredPlayer,"codename","groupType");
        return playerMapper.toPlayerResponseDto(recoveredPlayer);
    }

    private void validateFieldsUpdateConflict(Player player, Player recoveredPlayer) {
        if(playerEmailExistsAndBelongsToAnotherInstance(player.getEmail(),recoveredPlayer)){
            throw new PlayerEmailExistsException("Player with email: "+ player.getEmail()+" already exists!");
        }
    }

    private boolean playerEmailExistsAndBelongsToAnotherInstance(String email,Player recoveredPlayer) {
        return playerRepository.existsByEmailIgnoreCase(email) && !email.equalsIgnoreCase(recoveredPlayer.getEmail());
    }

    //METHOD NOT REQUIRED
    /*@Override
    @Transactional
    public void removePlayer(Long id) {
        Player player= findPlayerById(id);
        playerRepository.delete(player);
        codenameHandler.resetCodename(player.getCodename(),player.getGroupType());
    }*/

}
