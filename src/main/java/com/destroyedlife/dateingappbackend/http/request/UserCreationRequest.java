package com.destroyedlife.dateingappbackend.http.request;

import com.destroyedlife.dateingappbackend.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserCreationRequest {

    @NotEmpty
    private String email;

    @NotEmpty
    private String name;

    @NotEmpty
    private String nickname;

    @NotEmpty
    private String password;

    @NotNull
    private Role role;

    @NotNull
    private LocalDateTime createdAt;
}
