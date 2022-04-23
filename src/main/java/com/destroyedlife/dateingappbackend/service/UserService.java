package com.destroyedlife.dateingappbackend.service;

import com.destroyedlife.dateingappbackend.entity.User;
import com.destroyedlife.dateingappbackend.http.exception.ApiException;
import com.destroyedlife.dateingappbackend.http.exception.ErrorCode;
import com.destroyedlife.dateingappbackend.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtTokenService tokenService;

    public UserService (
        UserRepository repository,
        BCryptPasswordEncoder passwordEncoder,
        JwtTokenService tokenService
    ) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public String authentication(String email, String password) {
        User user = repository.findByEmail(email);

        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new ApiException(ErrorCode.INVALID_EMAIL_OR_PASSWORD);
        }

        return tokenService.createToken(user);
    }
}
