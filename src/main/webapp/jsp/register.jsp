<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html lang="en">
    <head>
        <title>Register</title>
    </head>

    <body>
        <c:if test="${not empty error}">
            <c:out value="${error}"/>
        </c:if>

        <h2>Register</h2>

        <form action="${pageContext.request.contextPath}/register" method="post">
            <label>Name:</label><br>
            <input type="text" name="name" required><br><br>

             <label>Email:</label><br>
             <input type="email" name="email" required><br><br>

             <label>Password:</label><br>
             <input type="password" name="password" required><br><br>

             <button type="submit">Register</button>
        </form>
        <p>
        Already have an account?
        <a href="${pageContext.request.contextPath}/jsp/login.jsp">Login</a>
        </p>
    </body>
</html>