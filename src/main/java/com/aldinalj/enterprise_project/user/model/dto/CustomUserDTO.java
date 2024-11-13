package com.aldinalj.enterprise_project.user.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomUserDTO(
        @NotBlank
        @Size(min = 4, max = 20, message = "Your username must be between 4-20 characters")
        String username,

        @NotBlank
        @Size(min = 8, max = 30, message = "Your password must be between 8-30 characters")
        String password

) {

}
