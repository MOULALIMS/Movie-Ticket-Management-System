package com.example.demo.controller;

import com.example.demo.error.GlobalException;
import com.example.demo.modal.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
    private UserService userService;

    // Register new user
	 @PostMapping("/register")
	    public ResponseEntity<User> registerUser(@RequestBody User user) throws GlobalException {
	        User newUser = userService.createUser(user);
	        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	    }

	    // User login (expects email and userPassword)
	
	 @PostMapping("/login")
	 public ResponseEntity<User> loginUser(@RequestBody Map<String, String> credentials) throws GlobalException {
	   if (!credentials.containsKey("email") || !credentials.containsKey("userPassword")) {
	     throw new GlobalException("Email and password are required");
	   }
	   
	   User user = userService.authenticateUser(
	     credentials.get("email"),
	     credentials.get("userPassword")
	   );
	   return ResponseEntity.ok(user);
	 }

    // Get user by ID
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Integer userId) throws GlobalException {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    // Update user profile
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(
        @PathVariable Integer userId,
        @RequestBody User userDetails
    ) throws GlobalException {
        return ResponseEntity.ok(userService.updateUser(userId, userDetails));
    }

    // Delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) throws GlobalException {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    // Change password
    @PutMapping("/{userId}/change-password")
    public ResponseEntity<Void> changePassword(
        @PathVariable Integer userId,
        @RequestBody Map<String, String> passwords
    ) throws GlobalException {
        userService.changePassword(
            userId,
            passwords.get("oldPassword"),
            passwords.get("newPassword")
        );
        return ResponseEntity.ok().build();
    }
}
