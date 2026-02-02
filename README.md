# User Management System

A Java-based **User Management System** built using **Servlets, JSP, JDBC**, and **MySQL**.  
The application provides secure user authentication, profile management, and password management following MVC and layered architecture principles.

---

##  Features

- User Registration
- User Login & Logout
- Authentication Filter
- User Dashboard
- Admin Panel
- Profile View & Edit
- Change Password


---

## Tech Stack

- **Java (JDK 8+)**
- **Jakarta Servlet & JSP**
- **JDBC**
- **MySQL**
- **JSTL**
- **Maven**
- **HTML / CSS**
- **Apache Tomcat**

---

## Project Architecture

The project follows a **layered MVC architecture**:

```
src/main/
├── java/com/app/
│   ├── controller/      # Servlets handling requests
│   ├── service/         # Business logic
│   ├── dao/             # Database Access Objects
│   ├── model/           # Data models (User, Role Enum)
│   ├── filters/         # Authentication and Admin access filters
│   └── util/            # NEW: Helper classes (DBConnection, PasswordHasher)
├── webapp/
│   ├── css/             # Independent stylesheets
│   └── jsp/             # Application views
---
## Key Functionalities

### Authentication
- User Registration
- Login with role-based redirection
- Session-based authentication
- Secure password hashing

### Profile Management
- View Profile
- Edit Profile
    - Email uniqueness validation
    - Session data updated after edit

### Change Password
- Current password verification
- New password confirmation
- Prevents reusing old password

### Admin Features
- Admin-only access using filters
- View all users
- Delete users
    - Self-delete protection
- Session & role validation for every admin action

### Security
- Authentication Filter
- Admin Authorization Filter
- Session validation
- Password hashing & verification