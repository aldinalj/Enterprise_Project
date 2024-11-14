package com.aldinalj.enterprise_project.user.dao;

import com.aldinalj.enterprise_project.user.model.CustomUser;

import java.util.Optional;

public interface UserDAO {

    CustomUser save(CustomUser user);
    CustomUser update(CustomUser user);
    void delete(CustomUser user);
    Optional<CustomUser> findById(Long id);
    Optional<CustomUser> findByUsername(String username);
    boolean existsByUsername(String username);
}
