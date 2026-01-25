<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html lang="en">
    <head><title>login</title></head>
    <body>
        <h2>login</h2>

        <c:if test="${not empty error}">
            <c:out value="${error}" />
        </c:if>

        <form action="${pageContext.request.contextPath}/login" method="post">

            <label>Email:</label><br>
            <input type="email" name="email" required><br><br>

            <label>Password:</label><br>
            <input type="password" name="password" required><br><br>

            <button type="submit">Login</button>
        </form>
    </body>
</html>