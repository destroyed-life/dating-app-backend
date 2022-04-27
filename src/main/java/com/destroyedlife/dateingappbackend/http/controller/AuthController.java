package com.destroyedlife.dateingappbackend.http.controller;

import com.destroyedlife.dateingappbackend.http.request.UserLoginRequest;
import com.destroyedlife.dateingappbackend.http.response.UserLoginResponse;
import com.destroyedlife.dateingappbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping(value = "login")
    public UserLoginResponse login(@RequestBody @Validated UserLoginRequest request)
    {
        String token = userService.issueJwtToken(request.getEmail(), request.getPassword());
        return new UserLoginResponse(token);
    }
}
