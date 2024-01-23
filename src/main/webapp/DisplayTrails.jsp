<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/album/">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet">
	<title>Display Trails</title>
	<link rel="stylesheet" href="TrailStyle.css">
	<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.1/css/bootstrap.min.css" integrity="sha512-T584yQ/tdRR5QwOpfvDfVQUidzfgc2339Lc8uBDtcp/wYu80d7jwBgAxbyMh0a9YM9F8N3tdErpFI8iaGx6x5g==" crossorigin="anonymous" referrerpolicy="no-referrer">
	<!-- JQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<!-- Bootstrap JS -->
    <!-- script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.1/js/bootstrap.min.js" integrity="sha512-UR25UO94eTnCVwjbXozyeVd6ZqpaAE9naiEUBK/A+QDbfSTQFhPGj5lOR6d8tsgbBk84Ggb5A3EkjsOgPRPcKA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script> -->
	<!--  link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/js/all.min.js" crossorigin="anonymous">-->

	<!-- Load font awesome icons -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">

	<!-- Bootstrap Dropdown -->
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

    
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
		          <a class="nav-link" href="displaytrails">TRAILS</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link" href="#">YOUR PROFILE</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link" href="login">LOGOUT</a>
		        </li>
		
		      </ul>
		    </div>
		</nav>
	</section>
<section id="main-banner">
	<div class="container">
	<div class="row text-center">
	<div class="col-md-6">
	<p class="banner-title"> Find Trails </p>
	<p class="banner-subtext"> Select a trail from the list below to learn more!</p>
	<p>
          <a href="index.html" class="btn btn-warning my-2">Return to Homepage</a>
      </p>
	</div>
	</div>
	</div>
	<img src="resources/wave1 (1).png" class="wave-img">
</section>
<section id="drop-down">
<div class="dropdown">
  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    FILTER BY DIFFICULTY </button>
     <ul class="dropdown-menu">
    <li><a class="dropdown-item" href="displaytrails">ALL</a></li>
    <li><a class="dropdown-item" href="displaytrails?difficulty=EASY">EASY</a></li>
    <li><a class="dropdown-item" href="displaytrails?difficulty=MEDIUM">MEDIUM</a></li>
    <li><a class="dropdown-item" href="displaytrails?difficulty=HARD">HARD</a></li>
  </ul>
   
    
  </div>
  </div>
</section>
<main>
  <div class="album py-5 bg-light">
    <div class="container">
      <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">

	<c:forEach begin="0" end="9" var="index">

        <div class="col">
          <div class="card shadow-sm">
          <c:choose>
	    <c:when test="${trailsData.ids[index]=='1'}">
	           <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false">
	                    <image href="resources/DiscoveryPark.jpeg" width="100%" height="225"
	                           preserveAspectRatio="xMidYMid slice" />
	                </svg>
	    </c:when>    
	    <c:when test="${trailsData.ids[index]=='8'}">
	           <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false">
	                    <image href="resources/CarkeekPark4.jpeg" width="100%" height="100%"
	                           preserveAspectRatio="xMidYMid slice" />
	                </svg>
	    </c:when>    
	    <c:otherwise>
	           
            <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>
	                
	    </c:otherwise>
       </c:choose>

            <div class="card-body">
              <p id="demos" class="card-text"> <c:out value="${trailsData.names[index]}"/><br>
              <c:out value="${trailsData.description[index]}"/>
                       
              
              <div class="d-flex justify-content-between align-items-center">
                <div class="btn-group">
  <!--  here add the logic to have a link to the details of each trail use 
  trailsData.ids[index] to add a parameter to the url like in the difficulty dropdown but instead you use id -->
                  <button type="button" class="btn btn-sm btn-outline-secondary"><a class="dropdown-item" href="abouttrail?name=${trailsData.names[index]}">View trail details</a>
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
        
	</c:forEach>
</main>

  </body>
</html>