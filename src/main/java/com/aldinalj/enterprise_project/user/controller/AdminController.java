package com.aldinalj.enterprise_project.user.controller;

import com.aldinalj.enterprise_project.user.model.dto.CustomUserDTO;
import com.aldinalj.enterprise_project.user.model.dto.DeleteUserDTO;
import com.aldinalj.enterprise_project.user.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<Void> deleteUser(@Valid @ModelAttribute DeleteUserDTO deleteUserDTO) {

        userService.adminDeleteUser(deleteUserDTO.username());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/create-admin")
    public ResponseEntity<CustomUserDTO> registerAdmin(@Valid @RequestBody CustomUserDTO customUserDTO) {

        return userService.createAdmin(customUserDTO);

    }
}
