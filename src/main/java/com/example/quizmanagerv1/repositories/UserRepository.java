package com.example.quizmanagerv1.repositories;

import com.example.quizmanagerv1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository
        extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
