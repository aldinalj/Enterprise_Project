package com.aldinalj.enterprise_project;

import com.aldinalj.enterprise_project.user.controller.UserController;

import com.aldinalj.enterprise_project.user.model.CustomUser;
import com.aldinalj.enterprise_project.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static com.aldinalj.enterprise_project.user.authorities.UserRole.USER;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserControllerTest {

    private final MockMvc mockMvc;
    private final UserController userController;
    private final UserRepository userRepository;

    @Autowired
    public UserControllerTest(MockMvc mockMvc, UserController userController, UserRepository userRepository) {
        this.mockMvc = mockMvc;
        this.userController = userController;
        this.userRepository = userRepository;
    }

    @BeforeEach
    void setInitialUser() {

        CustomUser user = new CustomUser();
        user.setUsername("Peggy");
        user.setPassword("agentcarter");
        user.setUserRole(USER);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);

        userRepository.save(user);
    }

    @Test
    void contextLoads() throws Exception {
        assertThat(userController).isNotNull();
    }

    @Test
    void testRegisterUser() throws Exception {
        mockMvc.perform(post("/user/register")
                        .contentType("application/json")
                        .content(""" 
                                {
                                  "username": "batman",
                                  "password": "brucewayne"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("batman"));

    }

    @Test
    void testRegisterAlreadyExistingUser() throws Exception {
        mockMvc.perform(post("/user/register")
                        .contentType("application/json")
                        .content(""" 
                                {
                                  "username": "Peggy",
                                  "password": "margaret"
                                }
                                """))
                .andExpect(status().isConflict());
    }


    @Test
    @WithMockUser(username = "raven", password = "tarastrong")
    void testFetchCredentials() throws Exception {
        mockMvc.perform(get("/user/test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("raven"))
                .andExpect(jsonPath("$.password").value("tarastrong"));

    }

    @Test
    @WithMockUser(username = "Peggy")
    void testDeleteAccount() throws Exception {
        mockMvc.perform(delete("/user/delete-account")
                )
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteAccountIfUnauthorized() throws Exception {
        mockMvc.perform(delete("/user/delete-account"))
                .andExpect(status().isUnauthorized());
    }
}
