<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Get Trail Ranking By Crime Frequency</title>
</head>
<body>
<h1>Trail ranking By Crime Frequency</h1>
<form action="getTrailsCrimeRateByYear" method="post">
    <p>
        <label for="numberOfYears">numberOfYears</label>
        <input id="numberOfYears" name="numberOfYears" value="${fn:escapeXml(param.numberOfYears)}">
    </p>
    <p>
        <input type="submit">
        <br/><br/><br/>
        <span id="successMessage"><b>${messages.success}</b></span>
    </p>
</form>
<br />

<h1>Trail Ranking</h1>
<table border="1">
    <tr>
        <th>Trail Name</th>
        <th>Observed Crime Count</th>
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
