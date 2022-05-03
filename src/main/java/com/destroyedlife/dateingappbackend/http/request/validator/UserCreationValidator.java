package com.destroyedlife.dateingappbackend.http.request.validator;

import com.destroyedlife.dateingappbackend.http.request.UserCreationRequest;
import com.destroyedlife.dateingappbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class UserCreationValidator {
    private final UserRepository userRepository;

    public void validate(UserCreationRequest creationForm, Errors errors) {
        if (userRepository.existsByNickname(creationForm.getNickname())) {
            errors.rejectValue("nickName", "wrongValue", "duplicate nickname");
        }
    }
}
