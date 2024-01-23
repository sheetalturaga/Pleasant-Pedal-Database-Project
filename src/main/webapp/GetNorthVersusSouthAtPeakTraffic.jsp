<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Get North Versus South Bound Traffic At Peak traffic</title>
</head>
<body>
<h1>North Versus South Traffic</h1>
<form action="getNorthVersusSouthAtPeakTime" method="post">
    <p>
        <input type="submit">
        <br/><br/><br/>
        <span id="successMessage"><b>${messages.success}</b></span>
    </p>
</form>
<br />

<h1>North Versus South Traffic</h1>
<table border="1">
    <tr>
        <th>Direction</th>
        <th>Traffic</th>
    </tr>
    pageContext.setAttribute("map", result);
    <c:forEach var="${entry}" items="${pageScope.map}">
        <tr>
            <td><c:out value="${entry.key}"/> </td>
            <td><c:out value="${entry.value}"/> </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
