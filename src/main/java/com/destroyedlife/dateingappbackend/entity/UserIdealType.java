package com.destroyedlife.dateingappbackend.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Users_Ideal_Type")
public class UserIdealType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @JsonBackReference
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    // @JsonBackReference
    @JoinColumn(name = "ideal_type_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private IdealType idealType;
}
