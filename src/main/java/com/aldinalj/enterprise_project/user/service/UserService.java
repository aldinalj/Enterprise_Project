package com.aldinalj.enterprise_project.user.service;

import com.aldinalj.enterprise_project.jwt.service.JwtService;
import com.aldinalj.enterprise_project.user.model.CustomUser;
import com.aldinalj.enterprise_project.user.model.dto.CustomUserDTO;
import com.aldinalj.enterprise_project.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static com.aldinalj.enterprise_project.user.authorities.UserRole.USER;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Transactional
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

    @Transactional
    public ResponseEntity<CustomUserDTO> deleteUser (UserDetails userDetails) {

        if (Objects.isNull(userDetails)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        CustomUser customUser = userRepository
                .findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(userDetails.getUsername() + "could not be found"));

        userRepository.delete(customUser);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new CustomUserDTO(customUser.getUsername()));

    }

    public String verify(CustomUser customUser) {

        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(customUser.getUsername(), customUser.getPassword()));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(customUser.getUsername());
        } else {
            return "Fail";
        }

    }
}
