package com.example.demo.service;

import com.example.demo.error.GlobalException;
import com.example.demo.modal.Admin;
import com.example.demo.repo.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImp implements AdminService {

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Admin createAdmin(Admin admin) throws GlobalException {
        if (adminRepo.existsByEmail(admin.getEmail())) {
            throw new GlobalException("Email already registered for admin.");
        }
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepo.save(admin);
    }

    @Override
    public Admin updateAdmin(Integer adminId, Admin adminDetails) throws GlobalException {
        Admin admin = adminRepo.findById(adminId)
                .orElseThrow(() -> new GlobalException("Admin not found with id: " + adminId));
        admin.setUsername(adminDetails.getUsername());
        admin.setEmail(adminDetails.getEmail());
        if (adminDetails.getPassword() != null && !adminDetails.getPassword().isEmpty()) {
            admin.setPassword(passwordEncoder.encode(adminDetails.getPassword()));
        }
        return adminRepo.save(admin);
    }

    @Override
    public void deleteAdmin(Integer adminId) throws GlobalException {
        if (!adminRepo.existsById(adminId)) {
            throw new GlobalException("Admin not found with id: " + adminId);
        }
        adminRepo.deleteById(adminId);
    }

    @Override
    public Admin getAdminById(Integer adminId) throws GlobalException {
        return adminRepo.findById(adminId)
                .orElseThrow(() -> new GlobalException("Admin not found with id: " + adminId));
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepo.findAll();
    }

    @Override
    public Admin authenticateAdmin(String username, String password) throws GlobalException {
        Admin admin = adminRepo.findByUsername(username);
        if(admin==null){
        	throw new GlobalException("Invalid Credentials!");
        }
        if (!passwordEncoder.matches(password, admin.getPassword())) {
            throw new GlobalException("Invalid credentials");
        }
        return admin;
    }
}
