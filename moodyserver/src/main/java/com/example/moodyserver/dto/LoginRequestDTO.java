package com.example.moodyserver.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class LoginRequestDTO {
    @NotBlank(message = "userName을 입력하세요")
    private String userName;

    @NotBlank(message = "password를 입력하세요")
    private String password;
}