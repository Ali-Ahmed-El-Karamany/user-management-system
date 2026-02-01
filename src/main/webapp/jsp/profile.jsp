<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html lang="en">
    <head><title>Profile</title></head>
    <body>
        <h2>My Profile</h2>

        <h3> ${loggedUser.name}</h3><br/>
        <h3>${loggedUser.email}</h3><br/>
        <h3>${loggedUser.role}</h3><br/>
        <h3>${loggedUser.registrationTime}</h3><br/>

        <a href="${pageContext.request.contextPath}/profile/edit">Edit Profile</a>
        <a href="${pageContext.request.contextPath}/jsp/dashboard.jsp">Back to Dashboard</a>
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </body>
</html>