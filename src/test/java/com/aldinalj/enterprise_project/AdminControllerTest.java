package com.aldinalj.enterprise_project;

import com.aldinalj.enterprise_project.user.model.CustomUser;
import com.aldinalj.enterprise_project.user.repository.UserRepository;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static com.aldinalj.enterprise_project.user.authorities.UserRole.USER;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AdminControllerTest {

    private final MockMvc mockMvc;
    private final UserRepository userRepository;

    @Autowired
    public AdminControllerTest(MockMvc mockMvc, UserRepository userRepository) {
        this.mockMvc = mockMvc;
        this.userRepository = userRepository;
    }

    @BeforeEach
    void setInitialUser() {

        CustomUser user = new CustomUser();
        user.setUsername("Yuji");
        user.setPassword("Itadori88");
        user.setUserRole(USER);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);

        userRepository.save(user);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testDeleteUserWhenAuthorized() throws Exception {

        mockMvc.perform(delete("/admin/delete-user")
                        .contentType("application/json")
                        .param("username", "Yuji")
                )
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(roles = "USER")
    void testUnauthorizedAccess() throws Exception {

        mockMvc.perform(get("/admin"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "USER")
    void testDeleteUserWhenUnauthorized() throws Exception {

        mockMvc.perform(delete("/admin/delete-user")
                        .contentType("application/json")
                        .param("username", "Yuji")
                )
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testRegisterAdmin() throws Exception {
        mockMvc.perform(post("/admin/create-admin")
                        .contentType("application/json")
                        .content(""" 
                                {
                                  "username": "zelda",
                                  "password": "hyrule1986"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("zelda"));

    }

    @Test
    @WithMockUser(roles = "USER")
    void testRegisterAdminWhenUnauthorized() throws Exception {
        mockMvc.perform(post("/admin/create-admin")
                        .contentType("application/json")
                        .content(""" 
                                {
                                  "username": "bucky",
                                  "password": "brooklyn1917"
                                }
                                """))
                .andExpect(status().isForbidden());
    }
}

