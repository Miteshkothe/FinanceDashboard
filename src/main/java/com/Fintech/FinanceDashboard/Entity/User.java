package com.Fintech.FinanceDashboard.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames ="username"),name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String status; //Active,InActive
    @Column(nullable = false)
    private String roles; //ROLE_VIEWER, ROLE_ANALYST, ROLE_ADMIN
    @Column(nullable = false)
    private String password;
    private LocalDateTime dateofjoining;
}
