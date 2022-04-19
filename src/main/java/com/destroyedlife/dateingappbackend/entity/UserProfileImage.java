package com.destroyedlife.dateingappbackend.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Users_Profile_Image")
public class UserProfileImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @JsonBackReference
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    // @JsonBackReference
    @JoinColumn(name = "profile_image_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProfileImage profileImage;
}
