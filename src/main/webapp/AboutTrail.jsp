<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>About Trail</title>
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
</head>
<body>
	<section id="nav-bar">
		<nav class="navbar navbar-expand-lg navbar-light">
		    <a class="navbar-brand" href="#"><img src="resources/rainbowlog-whitebg-modified.png"></a>
		    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" 
		    data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false"
		     aria-label="Toggle navigation">
		     <i class="fa fa-bars"></i>
		    </button>
		    <div class="collapse navbar-collapse" id="navbarNav">
		      <ul class="navbar-nav ml-auto">
		        <li class="nav-item">
		          <a class="nav-link" href="findtrails">TRAILS</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link" href="#">YOUR PROFILE</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link" href="#">LOGOUT</a> <!-----redirect to login page or index-->
		        </li>
		
		      </ul>
		    </div>
		</nav>
	</section>
<!------------- Form section --------------->
	<section class="h-100 h-custom" style="background-color: #d6d6d7;">
	  <div class="container py-5 h-100">
	    <div class="row d-flex justify-content-center align-items-center h-100">
	      <div class="col-lg-10 col-xl-8">
	        <div class="card rounded-3">
	          <img src="resources/trailtraffic.jpg"
	            class="w-100" style="border-top-left-radius: .3rem; border-top-right-radius: .3rem;"
	            alt="Sample photo">
	          <div class="card-body p-4 p-md-5">
	            <h3 class="mb-4 pb-2 pb-md-0 mb-md-5 px-md-2 text-center">Trail Information for ${TrailName}</h3>
	            <text class="mb-4 pb-2 pb-md-0 mb-md-5 px-md-2 text-center">${TrailDescription}</text><br />
	            <br /><img class="text-center align-middle" src="${TrailPicture}" width="630" height="630" alt="Picture of ${TrailName}"><br />

		          <div class="row">
                    <div class="col-lg-6 px-5">
                        <br /><label>Trail Name</label>
                    </div>
                    <div class="col-md-6 px-5">
                        <br /><p>${TrailName}</p>
                    </div>
                  </div>
                  <div class="row">
                     <div class="col-lg-6 px-5">
                         <label>Trail Id</label>
                     </div>
                     <div class="col-lg-6 px-5">
                         <p>${TrailId}</p>
                     </div>
                 </div>
                 <div class="row">
                     <div class="col-lg-6 px-5">
                         <label>Level Difficulty</label>
                     </div>
                     <div class="col-lg-6 px-5">
                         <p>${TrailDifficulty}</p>
                     </div>
                 </div>
                 <div class="row">
                     <div class="col-lg-6 px-5"">
                         <label>Distance</label>
                     </div>
                     <div class="col-lg-6 px-5">
                         <p>${TrailDistance}</p><br />
                     </div>
                 </div>
					<div class="row">
	                 <table class="table table-striped">
		                 <tr>
			                <th class="text-center">Peak Hour</th>
			             	<th class="text-center">Traffic Counter</th>
			            </tr>
			            <c:forEach items="${TrailTop5Peaks}" var="peak" >
			                <tr>
			                    <td class="text-center align-middle"><c:out value="${peak.getKey()}" /></td>
			                    <td class="text-center align-middle"><c:out value="${peak.getValue()}" /></td>
			                </tr>
			            </c:forEach>
					</table>
				</div>
                 </div>
	          </div>
	        </div>
	      </div>
	    </div>
	  </div>
	</section>
</body>
</html>
