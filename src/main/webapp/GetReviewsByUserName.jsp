<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Reviews</title>
</head>
<body>
	<form action="getuserreviews" method="post">
		<h1>User Reviews</h1>
		<p>
			<label for="username">UserName</label>
			<input id="username" name="username" value="">
		</p>
		<p>
			<input type="submit">
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<br/>
	<h1>Reviews made by the user</h1>
        <table border="1">
            <tr>
                <th>Created on</th>
                <th>Photo</th>
                <th>Rating</th>
                <th>Trail Id</th>
            </tr>
            <c:forEach items="${reviews}" var="review" >
                <tr>
                    <td><c:out value="${review.getCreated()}" /></td>
                    <td><img src="data:image/jpg;base64,${review.getImage()}" width="240" height="300"/></td>
                    <td><c:out value="${review.getRating()}" /></td>
                    <td><c:out value="${review.getTrail()}" /></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
