package com.Fintech.FinanceDashboard.Services;

import com.Fintech.FinanceDashboard.Entity.User;
import com.Fintech.FinanceDashboard.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user=userRepo.findByUsername(userName);
        if(user==null) {
            throw new UsernameNotFoundException(userName);
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRoles())
                .build();
    }
    public User findByUserName(String userName){
        User user=userRepo.findByUsername(userName);
        return user;
    }

}
