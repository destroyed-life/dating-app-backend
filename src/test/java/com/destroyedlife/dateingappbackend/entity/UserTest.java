package com.destroyedlife.dateingappbackend.entity;

import com.destroyedlife.dateingappbackend.enums.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    @DisplayName("빌더 존재하는지 테스트")
    public void Builder() {
        User user = User.builder()
                .name("이재선")
                .nickname("노아천사")
                .password("pw.1234")
                .role(Role.GUEST)
                .createdAt(LocalDateTime.of(2022, 4, 18, 21, 31))
                .build();

        assertThat(user).isNotNull();
    }
}
