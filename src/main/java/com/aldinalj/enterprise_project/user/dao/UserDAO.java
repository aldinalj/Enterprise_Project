package com.aldinalj.enterprise_project.user.dao;

import com.aldinalj.enterprise_project.user.model.CustomUser;

import java.util.Optional;

public interface UserDAO {

    Optional<CustomUser> findByUsername(String username);
}
