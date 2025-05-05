package com.example.demo.service;

import java.util.List;

import com.example.demo.error.GlobalException;
import com.example.demo.modal.User;

public interface UserService {

	User createUser(User user) throws GlobalException;

	User updateUser(Integer userId, User userDetails) throws GlobalException;

	void deleteUser(Integer userId) throws GlobalException;

	User getUserById(Integer userId) throws GlobalException;

	List<User> getAllUsers();

	User getUserByEmail(String email) throws GlobalException;

	User authenticateUser(String email, String password) throws GlobalException;

	void changePassword(Integer userId, String oldPassword, String newPassword) throws GlobalException;

}
