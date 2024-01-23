<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Get Daily Average Traffic By Trail Name</title>
</head>
<body>
<h1>Daily Average Traffic By Trail Name</h1>
<form action="getDailyAverageTrafficByTrailName" method="post">
    <p>
        <label for="trailName">trailName</label>
        <input id="trailName" name="trailName" value="${fn:escapeXml(param.trailName)}">
    </p>
    <p>
        <input type="submit">
        <br/><br/><br/>
        <span id="successMessage"><b>${messages.success}</b></span>
    </p>
</form>
<br />

<h1>Daily Average Traffic</h1>
<table border="1">
    <tr>
        <th>Trail Name</th>
        <th>Daily Average</th>
    </tr>
    pageContext.setAttribute("map", result);
    <c:forEach var="${entry}" items="${pageScope.map}">
        <tr>
            <td><c:out value="${entry.key}"/></td>
            <td><c:out value="${entry.value}"/> </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
