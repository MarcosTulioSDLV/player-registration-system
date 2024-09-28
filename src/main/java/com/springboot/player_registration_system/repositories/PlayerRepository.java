package com.springboot.player_registration_system.repositories;

import com.springboot.player_registration_system.enums.GroupType;
import com.springboot.player_registration_system.models.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Long> {

    boolean existsByEmailIgnoreCase(String email);

    Page<Player> findAllByGroupType(GroupType groupType,Pageable pageable);

    Page<Player> findAllByCodenameIgnoreCase(String codename,Pageable pageable);

}
