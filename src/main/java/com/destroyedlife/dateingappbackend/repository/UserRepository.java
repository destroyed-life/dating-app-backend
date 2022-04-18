package com.destroyedlife.dateingappbackend.repository;

import com.destroyedlife.dateingappbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
