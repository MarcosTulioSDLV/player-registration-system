package com.springboot.player_registration_system.models;

import com.springboot.player_registration_system.enums.GroupType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TB_PLAYER")
@AllArgsConstructor
@Getter @Setter @ToString
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = true)
    private String phoneNumber;

    @Column(nullable = false)
    private String codename;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GroupType groupType;

    public Player(){
    }

}
