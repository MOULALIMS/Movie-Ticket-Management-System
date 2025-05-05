package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modal.User;


public interface ResistrationRepo extends JpaRepository<User, Integer>
{

	public User findByEmail(String email);
	
	public User findByEmailAndUserPassword(String email , String userPassword);  
}
