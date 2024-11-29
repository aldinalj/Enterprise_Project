package com.aldinalj.enterprise_project.user.dao;

import com.aldinalj.enterprise_project.user.model.CustomUser;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDAO {

    @PersistenceContext
    private EntityManager entityManager;


    public Optional<CustomUser> findByUsername(String username) {
        String query = "SELECT u FROM CustomUser u WHERE u.username = :username";

        return entityManager.createQuery(query, CustomUser.class)
                .setParameter("username", username)
                .getResultStream()
                .findFirst();
    }

    public Optional<CustomUser> findByUsernameIgnoreCase(String username) {

        String query = "SELECT u FROM CustomUser u WHERE LOWER(u.username) = LOWER(:username)";

        return entityManager.createQuery(query, CustomUser.class)
                .setParameter("username", username)
                .getResultStream()
                .findFirst();
    }
}

