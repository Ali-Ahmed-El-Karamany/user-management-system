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

        <h2> Welcome, ${loggedUser.name}</h2><br/>
        <h2>Email: ${loggedUser.email}</h2><br/>
        <h2>Role: ${loggedUser.role}</h2><br/>

    </body>
</html>
