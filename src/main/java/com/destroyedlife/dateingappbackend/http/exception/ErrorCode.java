package com.destroyedlife.dateingappbackend.http.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // Auth 관련 에러
    INVALID_EMAIL_OR_PASSWORD(BAD_REQUEST, "이메일 또는 패스워드를 확인해주세요");

    private final HttpStatus httpStatus;
    private final String message;
}
