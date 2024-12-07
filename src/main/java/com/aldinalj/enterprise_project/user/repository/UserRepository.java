package com.aldinalj.enterprise_project.user.repository;

import com.aldinalj.enterprise_project.user.dao.UserDAO;
import com.aldinalj.enterprise_project.user.model.CustomUser;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public interface UserRepository extends JpaRepository<CustomUser, Long>, UserDAO {

}
