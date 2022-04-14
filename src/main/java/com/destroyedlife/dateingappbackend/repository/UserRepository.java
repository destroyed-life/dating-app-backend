package com.destroyedlife.dateingappbackend.repository;

import com.destroyedlife.dateingappbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface UserRepository extends JpaRepository<User, BigInteger> {
    User findOneByName(@Param("name") String name);
}
