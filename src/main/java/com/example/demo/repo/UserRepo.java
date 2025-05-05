package com.example.demo.repo;

import com.example.demo.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    
    // Security: Use this for login validation
    Optional<User> findByEmailAndUserPassword(String email, String userPassword);
}
