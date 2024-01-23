<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Crime Data</title>
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
		          <a class="nav-link" href="displaytrails">TRAILS</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link" href="aboutuser">YOUR PROFILE</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link" href="login">LOGOUT</a> <!-----redirect to login page or index-->
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
	          <img src="resources/trail2.jpeg"
	            class="w-100" style="border-top-left-radius: .3rem; border-top-right-radius: .3rem;"
	            alt="Sample photo">
	          <div class="card-body p-4 p-md-2">
	            <h3 class="mb-2 pb-2 pb-md-0 mb-md-2 px-md-2 text-center">NEIGHBORHOOD SAFETY INFORMATION</h3>
	            <p class="text-center">Based on the previously recorded data, the search gives you the ranking of the crime categories reported in the selected
	            neighborhood</p>
		        <form action="getcrimes" method="post">
		         	<div class="row text-center align-middle">
	                   <div class="col-md-5 text-center">
		                   <label for="neighborhood">Neighborhood</label>
					            <select name="neighborhood" id="neighborhood">
						            <option value="ALKI">ALKI</option>
						            <option value="FREMONT">FREMONT</option>
						            <option value="HIGHLAND PARK">HIGHLAND PARK</option>
						            <option value="MAGNOLIA">MAGNOLIA</option>
						            <option value="NORTH DELRIDGE">NORTH DELRIDGE</option>
						            <option value="SOUTH PARK">SOUTH PARK</option>
						            <option value="WALLINGFORD">WALLINGFORD</option>
						            <option value="BALLARD">BALLARD</option>
						            <option value="EASTLAKE">EASTLAKE</option>
						            <option value="INTERBAY">INTERBAY</option>
						        </select>
		                   </div>
		                 <div class="col-md-4 text-center">
		                   <label for="years">Past Years</label>
					            <select name="years" id="years">
						            <option value="1">1</option>
						            <option value="2">2</option>
						            <option value="3">3</option>
						            <option value="3">4</option>
						        </select>
		                   </div>
		                  <div class="col-md-3 pb-4 text-center">
		                   	<button id="search" name="search" type="submit" action="getcrimes" class="btn btn-outline-info">Search</button>
		                 </div>
		                                    
	                 </div>
                 </form>
			<div class="row">
                 <table class="table table-striped">
	                 <tr>
		                <th class="text-center">CRIME CATEGORY</th>
		             	<th class="text-center">COUNT</th>
		            </tr>
		            <c:forEach items="${results}" var="peak" >
		                <tr>
		                    <td class="text-left pl-4 align-middle"><c:out value="${peak.getKey()}" /></td>
		                    <td class="text-left pl-4 align-middle"><c:out value="${peak.getValue()}" /></td>
		                </tr>
		            </c:forEach>
				</table>
				</div>
                 </div>
	          </div>
	        </div>
	      </div>
	    </div>
	</section>
	

</body>
</html>