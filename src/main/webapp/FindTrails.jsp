<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find Trails</title>
</head>
<body>
	<h1>Find Trails</h1>
	<form action="findtrails" method="post">
		<h2>Search for a Trail by TrailName</h1>
		<p>
			<label for="trailName">TrailName</label>
			<input id="trailName" name="trailName" value="${fn:escapeXml(param.trailName)}">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>
=======
<title>Find a Trail</title>
</head>
<body>
	<form action="findtrails" method="post">
		<h1>Search for Trail by Difficulty</h1>
		<p>
			<label for="Difficulty"> Difficulty Level </label>
			<select name="Difficulty" id="Difficulty">
			<option value="SELECT">SELECT</option>
			<option value="BEGINNER">BEGINNER</option>
			<option value="INTERMEDIATE">INTERMEDIATE</option>
			<option value="ADVANCED">ADVANCED</option>
			</select>
		</p>
		<p>
			<input type="submit">
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<br/>
	<h1>Matching Trails</h1>
        <table border="1">
            <tr>
                <th>Trail Name</th>
                <th>Description</th>
                <th>Distance</th>
            </tr>
            <c:forEach items="${trails}" var="trail" >
                <tr>
                    <td><c:out value="${trail.getTrailName()}" /></td>
                    <td><c:out value="${trail.getDescription()}" /></td>
                    <td><c:out value="${trail.getDistance()}" /></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
