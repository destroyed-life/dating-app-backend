package com.destroyedlife.dateingappbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Getter
@Builder
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private BigInteger id;

    @Column(length = 50, nullable = false)
    private String name;

    public User() {}
}