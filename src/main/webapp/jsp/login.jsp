<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
    </head>
    <body>
        <div class="login-container">
            <div class="login-header">
                <h1>login</h1>
            </div>

            <c:if test="${not empty error}">
                <div class="error-message">
                    <c:out value="${error}" />
                </div>
            </c:if>

            <form action="${pageContext.request.contextPath}/login" method="post" class="login-form">

                <div class="form-group">
                    <label>Email Address</label>
                    <input type="email" name="email" id="email" class="form-input" placeholder="Enter your name">
                </div>

                <div class="form-group">
                    <label>Password</label>
                    <input type="password" name="password" id="password" class="form-input" placeholder="Enter your password">
                </div>

                <div class="form-action">
                    <button type="submit" class="btn btn-primary">Login</button>
                </div>
            </form>

            <footer class="login-footer">
                <p>Don't have an account?</p>
                <a href="${pageContext.request.contextPath}/register" class="btn btn-secondary">Create new account</a>
            </footer>
        </div>
    </body>
</html>