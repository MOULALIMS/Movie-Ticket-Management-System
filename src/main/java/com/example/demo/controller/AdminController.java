package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.error.GlobalException;
import com.example.demo.modal.Admin;
import com.example.demo.service.AdminService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<Admin> registerAdmin(@RequestBody Admin admin) throws GlobalException {
        Admin newAdmin = adminService.createAdmin(admin);
        return new ResponseEntity<>(newAdmin, HttpStatus.CREATED);
    }

    @GetMapping("/{adminId}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Integer adminId) throws GlobalException {
        Admin admin = adminService.getAdminById(adminId);
        return ResponseEntity.ok(admin);
    }
    
    @PostMapping("/login")
    public ResponseEntity<Admin> loginAdmin(@RequestBody Map<String, String> credentials) throws GlobalException {
        Admin admin = adminService.authenticateAdmin(
            credentials.get("username"),
            credentials.get("password")
        );
        return ResponseEntity.ok(admin);
    }

    @PutMapping("/{adminId}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable
    		Integer adminId, @RequestBody Admin adminDetails) 
        throws GlobalException {
        Admin updatedAdmin = adminService.updateAdmin(adminId, adminDetails);
        return ResponseEntity.ok(updatedAdmin);
    }
}
