package com.example.demo.service;

import java.util.List;

import com.example.demo.error.GlobalException;
import com.example.demo.modal.Admin;

public interface AdminService {

	Admin createAdmin(Admin admin) throws GlobalException;

	Admin updateAdmin(Integer adminId, Admin adminDetails) throws GlobalException;

	void deleteAdmin(Integer adminId) throws GlobalException;

	Admin getAdminById(Integer adminId) throws GlobalException;

	List<Admin> getAllAdmins();

	Admin authenticateAdmin(String username, String password) throws GlobalException;
}