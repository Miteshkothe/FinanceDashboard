package com.Fintech.FinanceDashboard.Controller;

import com.Fintech.FinanceDashboard.Entity.Records;
import com.Fintech.FinanceDashboard.Entity.User;
import com.Fintech.FinanceDashboard.Services.RecordEntry;
import com.Fintech.FinanceDashboard.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
public class DashBoard {
    @Autowired
    private UserService userService;
    @Autowired
    private RecordEntry recordEntry;
    @GetMapping()
    public ResponseEntity<?> getRecords(){
        try{
            Authentication auth= SecurityContextHolder.getContext().getAuthentication();
            String username= auth.getName();
            User user=userService.findByUserName(username);
            Map<String,Object> dashboard=new HashMap<>();
            Long totalExpence=recordEntry.getExpence();
            Long totalIncome=recordEntry.getIncome();
            Long Netbalance=totalIncome-totalExpence;
            List<Object[]> categoryWiseTotal=recordEntry.getCategoryWiseTotal();
            List<Object[]> recentActivity=recordEntry.getRecentActivity();
            List<Object[]> monthlyTrend=recordEntry.getMonthlyTrend();
            dashboard.put("totalExpence",totalExpence);
            dashboard.put("totalIncome",totalIncome);
            dashboard.put("netbalance",totalIncome-totalExpence);
            if(user.getRoles().equals("ROLE_VIEWER")&&user.getStatus().equals("Active")){
                return new ResponseEntity<>(dashboard, HttpStatus.OK);
            }else if(user.getStatus().equals("Active")){
                dashboard.put("categoryWise",categoryWiseTotal);
                dashboard.put("recentActivites",recentActivity);
                dashboard.put("monthlyTrend",monthlyTrend);
                return new ResponseEntity<>(dashboard, HttpStatus.OK);
            }return new ResponseEntity<>("You are not an active employee",HttpStatus.OK);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
