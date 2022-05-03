package com.destroyedlife.dateingappbackend.http.controller;

import com.destroyedlife.dateingappbackend.configuration.RestDocsConfiguration;
import com.destroyedlife.dateingappbackend.entity.User;
import com.destroyedlife.dateingappbackend.enums.Role;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

import static org.hamcrest.core.IsNull.nullValue;
import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Import(RestDocsConfiguration.class)
@ActiveProfiles("test")
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("정상적으로 회원가입하는 테스트")
    void create_user() throws Exception {
        User user = User.builder()
                .email("test1234@gmail.com")
                .name("테스트_이름")
                .nickname("테스트_닉네임")
                .password(passwordEncoder.encode("pw1234##"))
                .role(Role.GUEST)
                .createdAt(LocalDateTime.of(2022, 4, 29, 22, 30))
                .build();

        mockMvc.perform(post("/user/signUp")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaTypes.HAL_JSON)
                    .content(objectMapper.writeValueAsString(user)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, "application/hal+json;charset=UTF-8"))
                .andExpect(jsonPath("deletedAt").value(nullValue()))
                .andDo(document("create-user",
                        links(
                                linkWithRel("self").description("link to self"),
                                linkWithRel("query-user").description("link to query user"),
                                linkWithRel("update-user").description("link to update user"),
                                linkWithRel("profile").description("link to profile")
                        ),
                        requestHeaders(
                                headerWithName(HttpHeaders.ACCEPT).description("accept header"),
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("content type")
                        ),
                        requestFields(
                                fieldWithPath("id").description("id of new user"),
                                fieldWithPath("email").description("email of new user"),
                                fieldWithPath("name").description("name of new user"),
                                fieldWithPath("nickname").description("nickname of new user"),
                                fieldWithPath("password").description("password of new user"),
                                fieldWithPath("userProfileImages").description("userProfileImages"),
                                fieldWithPath("userIdealTypes").description("userIdealTypes"),
                                fieldWithPath("role").description("role of new user"),
                                fieldWithPath("gender").description("gender of new user"),
                                fieldWithPath("humanBodyType").description("humanBodyType of new user"),
                                fieldWithPath("bloodType").description("bloodType of new user"),
                                fieldWithPath("mbti").description("mbti of new user"),
                                fieldWithPath("height").description("height of new user"),
                                fieldWithPath("birthDay").description("birthDay of new user"),
                                fieldWithPath("baseAddress").description("baseAddress of new user"),
                                fieldWithPath("detailAddress").description("detailAddress of new user"),
                                fieldWithPath("religion").description("religion of new user"),
                                fieldWithPath("alcoholMention").description("alcoholMention of new user"),
                                fieldWithPath("smokeMention").description("smokeMention of new user"),
                                fieldWithPath("job").description("job of new user"),
                                fieldWithPath("hobby").description("hobby of new user"),
                                fieldWithPath("interest").description("interest of new user"),
                                fieldWithPath("introduce").description("introduce of new user"),
                                fieldWithPath("ifLoveTodo").description("ifLoveTodo of new user"),
                                fieldWithPath("createdAt").description("createdAt of new user"),
                                fieldWithPath("deletedAt").description("deletedAt of new user")
                        ),
                        responseHeaders(
                                headerWithName(HttpHeaders.LOCATION).description("location header"),
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("HAL+JSON")
                        ),
                        responseFields(
                                fieldWithPath("id").description("id of new user"),
                                fieldWithPath("email").description("email of new user"),
                                fieldWithPath("name").description("name of new user"),
                                fieldWithPath("nickname").description("nickname of new user"),
                                fieldWithPath("password").description("password of new user"),
                                fieldWithPath("userProfileImages").description("userProfileImages"),
                                fieldWithPath("userIdealTypes").description("userIdealTypes"),
                                fieldWithPath("role").description("role of new user"),
                                fieldWithPath("gender").description("gender of new user"),
                                fieldWithPath("humanBodyType").description("humanBodyType of new user"),
                                fieldWithPath("bloodType").description("bloodType of new user"),
                                fieldWithPath("mbti").description("mbti of new user"),
                                fieldWithPath("height").description("height of new user"),
                                fieldWithPath("birthDay").description("birthDay of new user"),
                                fieldWithPath("baseAddress").description("baseAddress of new user"),
                                fieldWithPath("detailAddress").description("detailAddress of new user"),
                                fieldWithPath("religion").description("religion of new user"),
                                fieldWithPath("alcoholMention").description("alcoholMention of new user"),
                                fieldWithPath("smokeMention").description("smokeMention of new user"),
                                fieldWithPath("job").description("job of new user"),
                                fieldWithPath("hobby").description("hobby of new user"),
                                fieldWithPath("interest").description("interest of new user"),
                                fieldWithPath("introduce").description("introduce of new user"),
                                fieldWithPath("ifLoveTodo").description("ifLoveTodo of new user"),
                                fieldWithPath("createdAt").description("createdAt of new user"),
                                fieldWithPath("deletedAt").description("deletedAt of new user"),
                                fieldWithPath("_links.self.href").description("link to self"),
                                fieldWithPath("_links.query-user.href").description("link to query user list"),
                                fieldWithPath("_links.update-user.href").description("link to update existing user"),
                                fieldWithPath("_links.profile.href").description("link to profile")
                        )
                ))
        ;
    }
}