package com.aldinalj.enterprise_project.user.controller;

import com.aldinalj.enterprise_project.config.security.CustomUserDetails;
import com.aldinalj.enterprise_project.user.model.CustomUser;
import com.aldinalj.enterprise_project.user.model.dto.CustomUserDTO;
import com.aldinalj.enterprise_project.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import static com.aldinalj.enterprise_project.user.authorities.UserRole.USER;

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

    @PostMapping("/login")
    public String login(@RequestBody CustomUser customUser) {

        System.out.println(customUser);

        return userService.verify(customUser);
    }

    @GetMapping("/test")
    public ResponseEntity<CustomUserDTO> testFetchUser(@AuthenticationPrincipal UserDetails userDetails) {

        /*System.out.println(userDetails.getUsername() + "/n"
                + userDetails.getPassword() + "/n"
                + userDetails.getAuthorities());
        ResponseEntity<UserDetails> response = ResponseEntity.ok(userDetails);
        System.out.println(response);*/

        if (userDetails != null) {
            CustomUserDTO customUerDTO = new CustomUserDTO(
                    userDetails.getUsername(),
                    userDetails.getPassword()
            );

            System.out.println("User " + customUerDTO.username() + " is authenticated");

            return ResponseEntity.ok(customUerDTO);
        } else {

            return ResponseEntity.ok().body(new CustomUserDTO("clarkkent", "superman"));
        }

    }
}
