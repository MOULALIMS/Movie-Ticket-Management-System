package com.example.demo.service;

import com.example.demo.error.GlobalException;
import com.example.demo.modal.User;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Regex patterns for validation
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+?[0-9]{10,15}$");

    /*@Override
    public User createUser(User user) throws GlobalException {
        // Validation checks
        validateEmail(user.getEmail());
        validatePhoneNumber(user.getPhone());

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new GlobalException("Email already registered");
        }

        // Hash password before saving
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        return userRepository.save(user);
    }*/

    @Override
    public User updateUser(Integer userId, User userDetails) throws GlobalException {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new GlobalException("User not found with id: " + userId));

        // Validate email if changing
        if (!existingUser.getEmail().equals(userDetails.getEmail())) {
            validateEmail(userDetails.getEmail());
            if (userRepository.existsByEmail(userDetails.getEmail())) {
                throw new GlobalException("New email already registered");
            }
            existingUser.setEmail(userDetails.getEmail());
        }

        // Update fields
        existingUser.setUserName(userDetails.getUserName());
        existingUser.setPhone(validatePhoneNumber(userDetails.getPhone()));

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Integer userId) throws GlobalException {
        if (!userRepository.existsById(userId)) {
            throw new GlobalException("User not found with id: " + userId);
        }
        userRepository.deleteById(userId);
    }

    @Override
    public User getUserById(Integer userId) throws GlobalException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new GlobalException("User not found with id: " + userId));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByEmail(String email) throws GlobalException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new GlobalException("User not found with email: " + email));
    }
/*
    @Override
    public User authenticateUser(String email, String password) throws GlobalException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new GlobalException("Invalid credentials"));

        if (!passwordEncoder.matches(password, user.getUserPassword())) {
            throw new GlobalException("Invalid credentials");
        }
        return user;
    }*/

    @Override
    public void changePassword(Integer userId, String oldPassword, String newPassword) throws GlobalException {
        User user = getUserById(userId);

        if (!passwordEncoder.matches(oldPassword, user.getUserPassword())) {
            throw new GlobalException("Old password is incorrect");
        }

        user.setUserPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    // Validation methods
    private void validateEmail(String email) throws GlobalException {
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new GlobalException("Invalid email format");
        }
    }

    private String validatePhoneNumber(String phone) throws GlobalException {
        if (phone != null && !phone.isEmpty()) {
            if (!PHONE_PATTERN.matcher(phone).matches()) {
                throw new GlobalException("Invalid phone number format");
            }
        }
        return phone; // Returning validated phone number
    }
    
    @Override
    public User createUser(User user) throws GlobalException {
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        return userRepository.save(user);
    }

    @Override
    public User authenticateUser(String email, String rawPassword) throws GlobalException {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new GlobalException("User not found"));
        
        if (!passwordEncoder.matches(rawPassword, user.getUserPassword())) {
            throw new GlobalException("Invalid password");
        }
        return user;
    }
}
