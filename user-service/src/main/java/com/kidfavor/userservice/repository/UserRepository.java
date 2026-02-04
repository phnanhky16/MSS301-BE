package com.kidfavor.userservice.repository;

import com.kidfavor.userservice.entity.User;
import com.kidfavor.userservice.entity.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByStatus(Boolean status);

    List<User> findByRole(Role role);
}
