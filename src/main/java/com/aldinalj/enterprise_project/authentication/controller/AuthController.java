package com.aldinalj.enterprise_project.authentication.controller;

import com.aldinalj.enterprise_project.authentication.dto.AuthResponseDTO;
import com.aldinalj.enterprise_project.authentication.service.AuthService;
import com.aldinalj.enterprise_project.user.model.dto.CustomUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody CustomUserDTO customUserDTO) {

        System.out.println(customUserDTO);

        return ResponseEntity.ok(authService.verify(customUserDTO));
    }
}
