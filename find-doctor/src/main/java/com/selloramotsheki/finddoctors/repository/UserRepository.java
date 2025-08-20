package com.selloramotsheki.finddoctors.repository;

import com.selloramotsheki.finddoctors.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
