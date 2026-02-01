<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminDashboard.css">
</head>
<body>

    <nav class="admin-nav">
        <div class="nav-brand">Admin Dashboard</div>
        <div class="nav-actions">
            <a href="${pageContext.request.contextPath}/profile">My Profile</a>
            <a href="${pageContext.request.contextPath}/jsp/dashboard.jsp">Dashboard</a>
            <a href="${pageContext.request.contextPath}/logout" class="logout-link">Logout</a>
        </div>
    </nav>

    <main class="admin-container">
        <header class="admin-header">
            <h2>User Management Panel</h2>
            <p>View, manage, and remove system users.</p>
        </header>

        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>
        <c:if test="${not empty message}">
            <div class="alert alert-success">${message}</div>
        </c:if>

        <div class="table-responsive">
            <table class="user-table">
                <th>
                    <tr>
                        <th>ID</th>
                        <th>User Name</th>
                        <th>Email Address</th>
                        <th>Role</th>
                        <th>Registered On</th>
                        <th class="text-center">Actions</th>
                    </tr>
                </t>
                <tb>
                    <c:forEach var="user" items="${usersList}">
                        <tr>
                            <td class="id-cell">#${user.id}</td>
                            <td class="name-cell"><strong>${user.name}</strong></td>
                            <td>${user.email}</td>
                            <td><span class="role-badge ${fn:toLowerCase(user.role)}">${user.role}</span></td>
                            <td>${user.registrationTime}</td>
                            <td class="text-center">
                                <c:choose>
                                    <c:when test="${user.id != loggedUser.id}">
                                        <form action="${pageContext.request.contextPath}/admin/deleteUser" method="post" onsubmit="return confirm('Are you sure you want to delete this user?');">
                                            <input type="hidden" name="userId" value="${user.id}"/>
                                            <button type="submit" class="btn-delete">Delete</button>
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="self-tag">You</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </t>
            </table>
        </div>
    </main>

</body>
</html>