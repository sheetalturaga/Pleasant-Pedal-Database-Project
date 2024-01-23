<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Get Crime Category Ranking by Trail</title>
</head>
<body>
	<h1>Get Crime Category Ranking</h1>
	<form action="getCrimeCategoryRankingByTrail" method="post">
	    <p>
	        <label for="trailName">TrailName</label>
	        <input id="trailName" name="TrailName" value="${fn:escapeXml(param.trailName)}">
	    </p>
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
	
	<h1>Crime Category Ranking</h1>
	<table border="1">
	    <tr>
	        <th>Crime Category Name</th>
	        <th>Observed Crime Count</th>
	    </tr>
	    <c:forEach var="${entry}" items="${result}">
	        <tr>
	            <td><c:out value="${entry.key}"/></td>
	            <td><c:out value="${entry.value}"/> </td>
	        </tr>
	    </c:forEach>
	</table>
</body>
</html>
