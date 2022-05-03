package com.destroyedlife.dateingappbackend.service;

import com.destroyedlife.dateingappbackend.entity.User;
import com.destroyedlife.dateingappbackend.enums.Role;
import com.destroyedlife.dateingappbackend.http.exception.ApiException;
import com.destroyedlife.dateingappbackend.http.exception.ErrorCode;
import com.destroyedlife.dateingappbackend.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@ActiveProfiles("test")
class UserServiceTest {
    @MockBean
    private JwtTokenService jwtTokenService;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private final String email = "test@test.com";
    private final String password = "password";

    @Nested
    @DisplayName("IssueJwtToken Method 테스트")
    public class TestOfIssueJwtToken {

        @Test
        @DisplayName("존재하지 않는 메일인 경우")
        public void testExceptionWhenInvalidEmail()
        {
            // Given
            given(userRepository.findByEmail(email)).willReturn(Optional.empty());

            // When && Then
            ApiException ex = assertThrowsExactly(ApiException.class, () ->
                userService.issueJwtToken(email, password)
            );

            assertEquals(ErrorCode.INVALID_EMAIL_OR_PASSWORD.getHttpStatus(), ex.getHttpStatus());
            assertEquals(ErrorCode.INVALID_EMAIL_OR_PASSWORD.getMessage(), ex.getMessage());
        }

        @Test
        @DisplayName("패스워드가 맞지 않는 경우 테스트")
        public void testExceptionWhenInvalidPassword()
        {
            // Given
            User user = User.builder()
                .id(100L)
                .createdAt(LocalDateTime.now())
                .name("권강혁")
                .email(email)
                .password(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password))
                .nickname("베러")
                .role(Role.MEMBER)
                .build();

            given(userRepository.findByEmail(email)).willReturn(Optional.ofNullable(user));

            // When && Then
            ApiException ex = assertThrowsExactly(ApiException.class, () ->
                userService.issueJwtToken(email, "kekekekekeke")
            );

            assertEquals(ErrorCode.INVALID_EMAIL_OR_PASSWORD.getHttpStatus(), ex.getHttpStatus());
            assertEquals(ErrorCode.INVALID_EMAIL_OR_PASSWORD.getMessage(), ex.getMessage());
        }

        @Test
        @DisplayName("이메일과 패스워드가 일치하는 경우 토큰을 반환한다")
        public void testReturnToken()
        {
            // Given
            User user = User.builder()
                .id(100L)
                .createdAt(LocalDateTime.now())
                .name("권강혁")
                .email(email)
                .password(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password))
                .nickname("베러")
                .role(Role.MEMBER)
                .build();

            String mockToken = "hi_this_is_test_token";

            given(userRepository.findByEmail(email)).willReturn(Optional.ofNullable(user));
            given(jwtTokenService.createToken(user)).willReturn(mockToken);

            // When
            String token = userService.issueJwtToken(email, password);

            // Then
            assertEquals(mockToken, token);
        }
    }

    @Nested
    @DisplayName("IssueJwtToken Method 테스트")
    public class TestOfGetUserFromJwtToken {

        @Test
        @DisplayName("토큰을 전달하는 경우 토큰의 Subject로 유저를 검색한다")
        public void testOfSuccess() {
            // Given
            String email = "test@test.com";
            User user = User.builder()
                .id(100L)
                .createdAt(LocalDateTime.now())
                .name("권강혁")
                .email(email)
                .password(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password))
                .nickname("베러")
                .role(Role.MEMBER)
                .build();

            String token = "this_is_token";

            given(jwtTokenService.getEmailFromToken(token)).willReturn(email);
            given(userRepository.findByEmail(email)).willReturn(Optional.ofNullable(user));

            // When
            User result = userService.getUserFromJwtToken(token);

            // Then
            assertEquals(user, result);
        }


        @Test
        @DisplayName("존재하지 않는 유저의 토큰은 예외를 발생시킨다")
        public void testWhenException() {
            // Given
            String email = "test@test.com";
            String token = "this_is_token";

            given(jwtTokenService.getEmailFromToken(token)).willReturn(email);
            given(userRepository.findByEmail(email)).willReturn(Optional.empty());

            // When && Then
            ApiException ex = assertThrowsExactly(ApiException.class, () ->
                userService.getUserFromJwtToken(token)
            );

            assertEquals(ErrorCode.INVALID_TOKEN.getHttpStatus(), ex.getHttpStatus());
            assertEquals(ErrorCode.INVALID_TOKEN.getMessage(), ex.getMessage());
        }
    }
}
