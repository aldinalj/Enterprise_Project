package com.aldinalj.enterprise_project.user.service;

import com.aldinalj.enterprise_project.user.model.CustomUser;
import com.aldinalj.enterprise_project.user.model.dto.CustomUserDTO;
import com.aldinalj.enterprise_project.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.aldinalj.enterprise_project.user.authorities.UserRole.USER;

@Service
public class CustomUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<CustomUserDTO> createUser(CustomUserDTO customUserDTO) {

        CustomUser customUser = new CustomUser(
                customUserDTO.username(),
                passwordEncoder.encode(customUserDTO.password()),
                USER,
                true,
                true,
                true,
                true
        );

        if (userRepository.findByUsername(customUser.getUsername()).isPresent()) {

            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        userRepository.save(customUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(customUserDTO);

    }
}
