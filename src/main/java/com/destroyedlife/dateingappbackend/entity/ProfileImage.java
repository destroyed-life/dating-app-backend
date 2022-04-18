package com.destroyedlife.dateingappbackend.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProfileImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "profileImage", cascade = CascadeType.ALL)
    private Set<UserProfileImage> userProfileImages = new HashSet<>();

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
