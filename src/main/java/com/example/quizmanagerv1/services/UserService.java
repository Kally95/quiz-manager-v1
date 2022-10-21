package com.example.quizmanagerv1.services;

import com.example.quizmanagerv1.exception.ResourceNotFoundException;
import com.example.quizmanagerv1.model.User;
import com.example.quizmanagerv1.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.quizmanagerv1.utils.Capitalise.capitaliseData;

@AllArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    public User findUserById(Long userId) throws ResourceNotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with " + userId + " Not found"));
    }

    public User getUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUsername(capitaliseData(user.getUsername()));
        return userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    // Create method to be called by login controller method to validate user login

}
