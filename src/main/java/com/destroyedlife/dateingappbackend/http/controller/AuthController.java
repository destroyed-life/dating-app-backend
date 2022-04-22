package com.destroyedlife.dateingappbackend.http.controller;

import com.destroyedlife.dateingappbackend.http.request.UserLoginRequest;
import com.destroyedlife.dateingappbackend.http.response.UserLoginResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping(value = "login")
    public UserLoginResponse login(@RequestBody @Validated UserLoginRequest request)
    {
        UserLoginResponse response = new UserLoginResponse(request.getEmail());

        // TODO : 로그인 서비스 구현

        return response;
    }
}
