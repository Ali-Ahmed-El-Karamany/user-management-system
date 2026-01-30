<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html lang="en">
    <head>
        <title>Dashboard</title>
    </head>

    <body>
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
        <hr>

        <c:choose>
            <c:when test="${loggedUser.role == 'ADMIN'}">
                <h2> Welcome, ${loggedUser.name}</h2><br/>
                <h2>Email: ${loggedUser.email}</h2><br/>
                <h2>Role: ${loggedUser.role}</h2><br/>
                <a href="${pageContext.request.contextPath}/admin/dashboard">Users List</a>
            </c:when>

            <c:otherwise>
                <h2> Welcome, ${loggedUser.name}</h2><br/>
                <h2>Email: ${loggedUser.email}</h2><br/>
                <h2>Role: ${loggedUser.role}</h2><br/>
            </c:otherwise>
        </c:choose>

    </body>
</html>
