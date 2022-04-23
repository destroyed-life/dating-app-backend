package com.destroyedlife.dateingappbackend.service;

import com.destroyedlife.dateingappbackend.entity.User;
import com.destroyedlife.dateingappbackend.http.exception.ApiException;
import com.destroyedlife.dateingappbackend.http.exception.ErrorCode;
import com.destroyedlife.dateingappbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService tokenService;

    public String authentication(String email, String password) {
        User user = repository.findByEmail(email)
            .orElseThrow(() -> new ApiException(ErrorCode.INVALID_EMAIL_OR_PASSWORD));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ApiException(ErrorCode.INVALID_EMAIL_OR_PASSWORD);
        }

        return tokenService.createToken(user);
    }
}
