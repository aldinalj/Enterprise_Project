package com.aldinalj.enterprise_project.user.model.dto;

import jakarta.validation.constraints.NotBlank;

public record DeleteUserDTO(

        @NotBlank(message = "Your username cannot be blank")
        String username
) {
}
