package com.destroyedlife.dateingappbackend.entity;

import com.destroyedlife.dateingappbackend.enums.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String email;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private final Set<UserProfileImage> userProfileImages = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private final Set<UserIdealType> userIdealTypes = new HashSet<>();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private HumanBodyType humanBodyType;

    @Enumerated(EnumType.STRING)
    private BloodType bloodType;

    @Enumerated(EnumType.STRING)
    private MBTI mbti;

    private double height;

    private LocalDate birthDay;

    private String baseAddress;

    private String detailAddress;

    private String religion;

    private String alcoholMention;

    private String smokeMention;

    private String job;

    @Lob @Basic(fetch = FetchType.LAZY)
    private String hobby;

    @Lob @Basic(fetch = FetchType.LAZY)
    private String interest;

    @Lob @Basic(fetch = FetchType.LAZY)
    private String introduce;

    @Lob @Basic(fetch = FetchType.LAZY)
    private String ifLoveTodo;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime deletedAt;
}
