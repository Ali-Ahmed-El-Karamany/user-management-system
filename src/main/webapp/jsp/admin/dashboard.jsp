<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html lang="en">
    <head>
        <title>Admin Dashboard</title>
    </head>

    <body>
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
        <hr>

        <h2> Admin Dashboard</h2>
        <hr>

        <table border="1">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Role</th>
                <th>Registration Date</th>
                <th>Delete User</th>
            </tr>
            <c:forEach var="user" items="${usersList}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                    <td>${user.role}</td>
                    <td>${user.registrationTime}</td>
                    <td>
                            <form action="${pageContext.request.contextPath}/admin/deleteUser" method="post">
                                <input type="hidden" name="userId" value="${user.id}"/>
                                <button type="submit">Delete</button>
                            </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <c:if test="not empty error">
            <c:out value="${error}"/>
        </c:if>
    </body>
</html>
