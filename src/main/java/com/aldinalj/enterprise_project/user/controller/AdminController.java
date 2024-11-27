package com.aldinalj.enterprise_project.user.controller;

import com.aldinalj.enterprise_project.user.service.UserService;
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

    @DeleteMapping("/deleteUser")
    public ResponseEntity<Void> deleteUser(@RequestParam String username) {

        userService.adminDeleteUser(username);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
