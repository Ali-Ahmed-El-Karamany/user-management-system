<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
</head>
<body>

    <nav class="navbar">
        <div class="nav-logo">User Management System</div>
        <ul class="nav-links">
            <li><a href="${pageContext.request.contextPath}/jsp/profile.jsp">My Profile</a></li>
            <c:if test="${loggedUser.role == 'ADMIN'}">
                <li><a href="${pageContext.request.contextPath}/admin/dashboard" class="admin-link">Manage All Users</a></li>
            </c:if>
            <li><a href="${pageContext.request.contextPath}/logout" class="logout-btn">Logout</a></li>
        </ul>
    </nav>

    <main class="dashboard-container">
        <header class="dashboard-header">
            <h1>Welcome back, <span class="highlight">${loggedUser.name}</span>!</h1>
            <p class="subtitle">You are successfully logged into your dashboard.</p>
        </header>

        <section class="dashboard-content">
            <div class="welcome-card">
                <h3>Getting Started</h3>
                <p>Use the navigation bar at the top to manage your account settings or view your profile details.</p>

                <c:if test="${loggedUser.role == 'ADMIN'}">
                    <div class="admin-panel">
                        <p><strong>Administrator Notice:</strong> You have access to the global user directory.</p>
                    </div>
                </c:if>
            </div>
        </section>
    </main>

</body>
</html>