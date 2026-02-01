<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Profile</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/editProfile.css">
</head>
<body>

    <nav class="navbar">
        <div class="nav-logo">User Management System</div>
        <ul class="nav-links">
            <li><a href="${pageContext.request.contextPath}/profile/edit">My Profile</a></li>
            <li><a href="${pageContext.request.contextPath}/logout" class="logout-link">Logout</a></li>
        </ul>
    </nav>

    <main class="edit-container">
        <div class="edit-card">
            <header class="edit-header">
                <h2>Update Your Information</h2>
                <p>Modify your name or email address below.</p>
            </header>

            <c:if test="${not empty error}">
                <div class="error-banner">
                    <c:out value="${error}"/>
                </div>
            </c:if>

            <form action="${pageContext.request.contextPath}/profile/edit" method="post" class="edit-form">

                <div class="form-group">
                    <label for="newName">Full Name</label>
                    <input type="text" id="newName" name="newName" value="${loggedUser.name}" class="form-input">
                </div>

                <div class="form-group">
                    <label for="newEmail">Email Address</label>
                    <input type="email" id="newEmail" name="newEmail" value="${loggedUser.email}" class="form-input">
                </div>

                <input type="hidden" name="userId" value="${loggedUser.id}"/>

                <div class="form-actions">
                    <button type="submit" class="btn-update">Save Changes</button>
                    <a href="${pageContext.request.contextPath}/profile" class="btn-cancel">Cancel</a>
                </div>
            </form>
        </div>
    </main>

</body>
</html>