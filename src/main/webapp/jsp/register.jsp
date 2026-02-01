<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Register</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css">
    </head>

    <body>
        <div class="register-container">

            <div class="register-header">
                <h1>Create new account</h1>
                <p>Please fill the data to create an account</p>
            </div>

            <c:if test="${not empty error}">
                <div class="error-message">
                    <c:out value="${error}"/>
                </div>
            </c:if>

            <form action="${pageContext.request.contextPath}/register" method="post" class="register-form">

                <div class="form-group">
                    <label>Full Name</label>
                    <input type="text" name="name" id="name" class="form-input" placeholder="Enter your name">
                </div>

                <div class="form-group">
                    <label>Email Address</label><br>
                    <input type="email" name="email" id="email" class="form-input" placeholder="Enter your email">
                </div>

                <div class="form-group">
                    <label>Password</label><br>
                    <input type="password" name="password" id="password" class="form-input" placeholder="Create your password">
                </div>

                <div class="form-action">
                    <button type="submit" class="reg-btn-main">Register</button>
                </div>

            </form>

            <div>
                <span>Already have an account?</span>
                <a href="${pageContext.request.contextPath}/jsp/login.jsp" class="register-link">Login</a>
            </div>
        </div>
    </body>
</html>