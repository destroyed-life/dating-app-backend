package com.destroyedlife.dateingappbackend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class IdealType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type;

    private String description;

    @OneToMany(mappedBy = "idealType", cascade = CascadeType.ALL)
    private Set<UserIdealType> idealTypes = new HashSet<>();
}
