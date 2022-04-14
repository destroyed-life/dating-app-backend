package com.destroyedlife.dateingappbackend.controller;

import com.destroyedlife.dateingappbackend.service.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class DefaultControllerTest {

    @MockBean
    private TestService testService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test() throws Exception {
        // Given
        String testString = "kk";
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/");
        given(testService.getTest()).willReturn(testString);

        // When
        ResultActions result = mockMvc.perform(request);

        // Then
        result.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(testString));
    }
}
