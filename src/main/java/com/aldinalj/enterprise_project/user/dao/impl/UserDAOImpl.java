package com.aldinalj.enterprise_project.user.dao.impl;

import com.aldinalj.enterprise_project.user.dao.UserDAO;
import com.aldinalj.enterprise_project.user.model.CustomUser;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public CustomUser save(CustomUser user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public CustomUser update(CustomUser user) {
        return user;
    }

    @Override
    public void delete(CustomUser user) {
    }

    @Override
    public Optional<CustomUser> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<CustomUser> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public boolean existsByUsername(String username) {
        return false;
    }
}
