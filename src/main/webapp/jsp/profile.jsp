<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Profile</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css">
</head>
<body>

    <nav class="navbar">
        <div class="nav-logo">User Management System</div>
        <ul class="nav-links">
            <c:if test="${loggedUser.role == 'ADMIN'}">
                <li><a href="${pageContext.request.contextPath}/admin/dashboard" class="admin-link">Manage All Users</a></li>
            </c:if>
            <li><a href="${pageContext.request.contextPath}/jsp/dashboard.jsp">Dashboard</a></li>
            <li><a href="${pageContext.request.contextPath}/logout" class="logout-link">Logout</a></li>
        </ul>
    </nav>

    <main class="profile-container">
        <div class="profile-card">
            <header class="profile-header">
                <div class="profile-avatar">
                    <span>${loggedUser.name.charAt(0)}</span>
                </div>
                <h2>User Profile</h2>
            </header>

            <section class="profile-details">
                <div class="detail-group">
                    <span class="detail-label">Full Name</span>
                    <p class="detail-value">${loggedUser.name}</p>
                </div>

                <div class="detail-group">
                    <span class="detail-label">Email Address</span>
                    <p class="detail-value">${loggedUser.email}</p>
                </div>

                <div class="detail-group">
                    <span class="detail-label">Account Role</span>
                    <p class="detail-value status-badge">${loggedUser.role}</p>
                </div>

                <div class="detail-group">
                    <span class="detail-label">Joined On</span>
                    <p class="detail-value">${loggedUser.registrationTime}</p>
                </div>
            </section>

            <footer class="profile-actions">
                <a href="${pageContext.request.contextPath}/profile/edit" class="btn btn-edit">Edit Profile</a>
            </footer>
        </div>
    </main>

</body>
</html>