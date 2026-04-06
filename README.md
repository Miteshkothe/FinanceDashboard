# 🔐 Spring Boot Role-Based Authentication & Authorization API

## 📌 Overview

This project is a **Spring Boot REST API** that implements **JWT-based authentication** and **role-based authorization** using Spring Security.

It secures endpoints using `@PreAuthorize` and ensures that only users with the appropriate roles (e.g., ADMIN, USER) can access specific resources.

---

## 🚀 Features

* 🔑 JWT-based Authentication
* 🔒 Role-Based Authorization (RBAC)
* 🛡️ Secure REST APIs using Spring Security
* 🎯 Method-level security with `@PreAuthorize`
* ⚡ Stateless session management
* 📦 Clean and scalable project structure

---

## 🛠️ Tech Stack

* Java
* Spring Boot
* Spring Security
* JWT (JSON Web Token)
* Maven

---

## 🔄 Authentication Flow

1. User logs in with credentials
2. Server validates user details
3. JWT token is generated with user roles
4. Client sends token in Authorization header
5. Spring Security validates token and extracts roles
6. Access is granted/denied based on roles

---

## 🔑 Role Management

Roles are stored with the `ROLE_` prefix:

```
ROLE_ADMIN
ROLE_USER
```

---

## 🔐 Authorization using @PreAuthorize

### Class-Level Example

```
@PreAuthorize("hasRole('ADMIN')")
@RestController
public class AdminController {
}
```

➡️ All endpoints require ADMIN role

---

### Method-Level Example

```
@PreAuthorize("hasRole('USER')")
@GetMapping("/user")
public String userAccess() {
    return "User content";
}
```

➡️ Only USER role can access

---

### Authority-Based Check

```
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
```

---

## ⚙️ Security Configuration

* Method security enabled using:

```
@EnableMethodSecurity
```

* JWT filter used to authenticate requests

---

## 📡 API Example

### 🔐 Login

```
POST /auth/login
```

### Request:

```
{
  "username": "admin",
  "password": "password"
}
```

### Response:

```
{
  "token": "JWT_TOKEN"
}
```

---

### 🔒 Protected Endpoint

```
GET /admin/dashboard
Authorization: Bearer <JWT_TOKEN>
```

---

## ❗ Common Issues & Fixes

### 🔴 403 Forbidden

**Cause:** Role mismatch

| Issue                    | Fix                         |
| ------------------------ | --------------------------- |
| Using `hasRole("ADMIN")` | Ensure role is `ROLE_ADMIN` |
| Role stored as `ADMIN`   | Use `hasAuthority("ADMIN")` |

---

### 🔴 @PreAuthorize not working

✔ Ensure:

```
@EnableMethodSecurity
```

---

### 🔴 Roles not applied

✔ Map roles correctly:

```
new SimpleGrantedAuthority(role)
```

---

## 📁 Project Structure

```
src/
 ├── controller/
 ├── service/
 ├── repository/
 ├── config/
 ├── security/
 └── model/
```

---

## ▶️ How to Run

1. Clone the repository

```
git clone <repo-url>
```

2. Navigate to project

```
cd project-folder
```

3. Run the application

```
mvn spring-boot:run
```

---

## 🧠 Best Practices

* ✅ Use `ROLE_` prefix for roles
* ✅ Do not pass roles as method parameters
* ✅ Secure endpoints using annotations
* ✅ Keep JWT stateless

---

## 📌 Future Improvements

* Refresh token implementation
* Role hierarchy (ADMIN > USER)
* OAuth2 integration
* Docker deployment

---

## 👨‍💻 Author

**Mitesh Kothe**

---

## ⭐ If you like this project

Give it a star ⭐ on GitHub!
