package com.aldinalj.enterprise_project.authentication.dto;

public record AuthenticationResponseDTO(
        String token,
        String role
){
}
