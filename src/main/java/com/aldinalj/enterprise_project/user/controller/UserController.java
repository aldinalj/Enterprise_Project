package com.aldinalj.enterprise_project.user.controller;

import com.aldinalj.enterprise_project.user.model.dto.CustomUserDTO;
import com.aldinalj.enterprise_project.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService customUserService) {
        this.userService = customUserService;
    }

    @PostMapping("/register")
    public ResponseEntity<CustomUserDTO> registerUser(@Valid @RequestBody CustomUserDTO customUserDTO) {

        return userService.createUser(customUserDTO);

    }

    @DeleteMapping("/delete-account")
    public ResponseEntity<CustomUserDTO> deleteAccount(@AuthenticationPrincipal UserDetails userDetails) {

        return userService.deleteUser(userDetails);
    }
}
