package com.Fintech.FinanceDashboard.Services;

import com.Fintech.FinanceDashboard.Entity.User;
import com.Fintech.FinanceDashboard.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserEntry {
    @Autowired
    private UserRepo userRepo;
    private static final PasswordEncoder passwordEncode=new BCryptPasswordEncoder();
    public void savenew(User user){
        try {
            user.setPassword(passwordEncode.encode(user.getPassword()));
            user.setDateofjoining(LocalDateTime.now());
            userRepo.save(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }public void save(User user){
        userRepo.save(user);
    }
    public void changeRole(String username,String role){
        User user=userRepo.findByUsername(username);
        user.setRoles(role);
        save(user);
    }public void changeStatus(String username,String status){
        User user=userRepo.findByUsername(username);
        user.setStatus(status);
        save(user);
    }
}
