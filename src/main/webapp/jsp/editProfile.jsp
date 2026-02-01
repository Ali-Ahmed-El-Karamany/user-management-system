<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html lang="en">
    <head><title>Edit Profile</title></head>
    <body>
        <form action="${pageContext.request.contextPath}/profile/edit" method="post">
            <label>Name:</label><br>
            <input type="text" name="newName" value="${loggedUser.name}" ><br><br>

            <label>Email:</label><br>
            <input type="email" name="newEmail" value="${loggedUser.email}"><br><br>

            <input type="hidden" name="userId" value="${loggedUser.id}"/>
            <button type="submit">Update</button>
        </form>

        <c:if test="${not empty error}">
            <c:out value="${error}"/>
        </c:if>

        <a href="${pageContext.request.contextPath}/jsp/profile.jsp">Back to Profile</a>
    </body>
</html>