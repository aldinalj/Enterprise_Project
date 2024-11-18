package com.aldinalj.enterprise_project.user.controller;

import com.aldinalj.enterprise_project.user.model.dto.CustomUserDTO;
import com.aldinalj.enterprise_project.user.service.CustomUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final CustomUserService customUserService;

    @Autowired
    public UserController(CustomUserService customUserService) {
        this.customUserService = customUserService;
    }

    @PostMapping("/register")
    public ResponseEntity<CustomUserDTO> registerUser(@Valid @RequestBody CustomUserDTO customUserDTO) {

        return customUserService.createUser(customUserDTO);

    }

    @DeleteMapping("/delete-account")
    public ResponseEntity<CustomUserDTO> deleteAccount(Authentication authentication) {

        return customUserService.deleteUser(authentication);
    }
}
