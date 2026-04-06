package com.Fintech.FinanceDashboard.Controller;

import com.Fintech.FinanceDashboard.Entity.User;
import com.Fintech.FinanceDashboard.Services.UserEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class UserCreation {
    @Autowired
    private UserEntry userEntry;
    @PostMapping("/createuser")
    public ResponseEntity<String> createUser(@RequestBody User user){
        try {
            userEntry.savenew(user);
            return new ResponseEntity<>("User Save", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PatchMapping("/role")
    public ResponseEntity<String> changeRole(@RequestBody User user){
        try{
            userEntry.changeRole(user.getUsername(),user.getRoles());
            return new ResponseEntity<>("User Save", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Not saved",HttpStatus.NOT_FOUND);
        }
    }
    @PatchMapping("/status")
    public ResponseEntity<String> changeStatus(@RequestBody User user){
        try{
            userEntry.changeStatus(user.getUsername(),user.getStatus());
            return new ResponseEntity<>("User Save", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Not saved",HttpStatus.NOT_FOUND);
        }
    }
}
