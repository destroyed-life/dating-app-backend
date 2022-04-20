package com.destroyedlife.dateingappbackend.http.controller;

import com.destroyedlife.dateingappbackend.http.request.UserLoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@DisplayName("AuthController test")
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Nested
    @DisplayName("Login method test")
    public class TestOfLoginMethod {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/");

        @Test
        @DisplayName("이메일이 비어있는 경우 검증")
        public void testValidationFailWhenEmailIsBlank() throws Exception {
            // Given
            UserLoginRequest request = new UserLoginRequest("", "1213123");
            String json = objectMapper.writeValueAsString(request);

            // When
            ResultActions result = mockMvc
                .perform(
                    post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json)
                );

            // Then
            result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("이메일을 입력해주세요"));
        }

        @Test
        @DisplayName("올바르지 않은 이메일 형식인 경우 검증")
        public void testValidationFailWhenEmailIsInvalid() throws Exception {
            // Given
            UserLoginRequest request = new UserLoginRequest("test.com", "1213123");
            String json = objectMapper.writeValueAsString(request);

            // When
            ResultActions result = mockMvc
                .perform(
                    post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json)
                );

            // Then
            result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("올바른 이메일 형식이 아닙니다"));
        }

        @Test
        @DisplayName("패스워드가 비어있는 경우 검증")
        public void testValidationFailWhenPasswordIsBlank() throws Exception {
            // Given
            UserLoginRequest request = new UserLoginRequest("abc@me.com", "");
            String json = objectMapper.writeValueAsString(request);

            // When
            ResultActions result = mockMvc
                .perform(
                    post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json)
                );

            // Then
            result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("비밀번호를 입력해주세요"));
        }

        @Test
        @DisplayName("정상 로그인 요청시 토큰 반환 테스트")
        public void testReturnToken() throws Exception {
            // Given
            UserLoginRequest request = new UserLoginRequest("abc@me.com", "password");
            String json = objectMapper.writeValueAsString(request);

            // When
            ResultActions result = mockMvc
                .perform(
                    post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json)
                );

            // Then
            result.andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("abc@me.com"));
        }
    }
}
