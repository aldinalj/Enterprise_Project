package com.aldinalj.enterprise_project.authentication.service;

import com.aldinalj.enterprise_project.authentication.dto.AuthenticationResponseDTO;
import com.aldinalj.enterprise_project.authentication.jwt.service.JwtService;
import com.aldinalj.enterprise_project.user.model.dto.CustomUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public AuthenticationResponseDTO verify(CustomUserDTO customUserDTO) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                customUserDTO.username(),
                                customUserDTO.password()
                        ));

        String generatedToken = jwtService.generateToken(customUserDTO.username());
        System.out.println("Generated token: " +  generatedToken);

        return new AuthenticationResponseDTO(
                generatedToken,
                authentication.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .filter(authority -> authority.startsWith("ROLE_"))
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException("User has no role."))
                        .substring(5)
        );
    }
}
