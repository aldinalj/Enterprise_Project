package com.aldinalj.enterprise_project.user.repository;

import com.aldinalj.enterprise_project.user.model.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<CustomUser, Long> {

}
