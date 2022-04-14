package com.destroyedlife.dateingappbackend.repository;

import com.destroyedlife.dateingappbackend.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository repository;

    @Test
    public void test()
    {
        // Given
        String expectName = "권강혁";
        User user = User.builder().name(expectName).build();
        repository.save(user);

        // When
        User findedUser = repository.findOneByName(expectName);

        // Then
        Assertions.assertSame(user.getId(), findedUser.getId());
    }
}
