package com.destroyedlife.dateingappbackend.http.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResponse {
    @Setter
    private String token;
    private final String tokenType = "Bearer";
}
