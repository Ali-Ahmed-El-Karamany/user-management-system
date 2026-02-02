<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Change Password</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/changePassword.css">
</head>
<body>

    <nav class="navbar">
        <div class="nav-logo">User Management System</div>
        <ul class="nav-links">
            <li><a href="${pageContext.request.contextPath}/jsp/profile.jsp">Back to Profile</a></li>
            <li><a href="${pageContext.request.contextPath}/logout" class="logout-link">Logout</a></li>
        </ul>
    </nav>

    <main class="password-container">
        <div class="password-card">
            <header class="password-header">
                <h2>Change Password</h2>
                <p>Ensure your account stays secure by using a strong password.</p>
            </header>

            <c:if test="${not empty error}">
                <div class="alert alert-error">${error}</div>
            </c:if>

            <form action="${pageContext.request.contextPath}/profile/changePassword" method="post" class="password-form">

                <div class="form-group">
                    <label for="oldPassword">Current Password</label>
                    <input type="password" id="oldPassword" name="oldPassword" placeholder="Enter current password">
                </div>

                <div class="form-group">
                    <label for="newPassword">New Password</label>
                    <input type="password" id="newPassword" name="newPassword" placeholder="Enter new Password">
                </div>

                <div class="form-group">
                    <label for="confirmPassword">Confirm New Password</label>
                    <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Re-type new password">
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn-submit">Update Password</button>
                    <a href="${pageContext.request.contextPath}/profile" class="btn-cancel">Cancel</a>
                </div>
            </form>
        </div>
    </main>

</body>
</html>