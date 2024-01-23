<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>About User</title>
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
		          <a class="nav-link" href="#">YOUR PROFILE</a>
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
	          <img src="resources/profile.jpeg"
	            class="w-100" style="border-top-left-radius: .3rem; border-top-right-radius: .3rem;"
	            alt="Sample photo">
	          <div class="card-body p-4 p-md-5">
	            <h3 class="mb-4 pb-2 pb-md-0 mb-md-5 px-md-2 text-center">Hi, ${firstname}</h3>
		          <div class="row">
                    <div class="col-lg-6 px-5">
                        <label>User Name</label>
                    </div>
                    <div class="col-md-6 px-5">
                        <p>${result.getUserName()}</p>
                    </div>
                  </div>
                  <div class="row">
                     <div class="col-lg-6 px-5">
                         <label>First Name</label>
                     </div>
                     <div class="col-lg-6 px-5">
                         <p>${result.getFirstName()}</p>
                     </div>
                 </div>
                 <div class="row">
                     <div class="col-lg-6 px-5">
                         <label>Last Name</label>
                     </div>
                     <div class="col-lg-6 px-5">
                         <p>${result.getLastName()}</p>
                     </div>
                 </div>
                 <div class="row">
                     <div class="col-lg-6 px-5"">
                         <label>Email</label>
                     </div>
                     <div class="col-lg-6 px-5">
                         <p>${result.getEmail()}</p>
                     </div>
                 </div>
                 <div class="row">
                     <div class="col-lg-6 px-5">
                         <label>Bio</label>
                     </div>
                     <div class="col-lg-6 px-5">
                         <p>${result.getBio()}</p>
                     </div>
                 </div>
                 <div class="row">
                     <div class="col-lg-6 px-5">
                         <label>Address</label>
                     </div>
                     <div class="col-lg-6 px-5">
                         <p>${result.getAddress()}</p>
                     </div>
                 </div>
                 <div class="row">
                     <div class="col-lg-6 px-5">
                         <label>Level</label>
                     </div>
                     <div class="col-lg-6 px-5"">
                         <p>${result.getLevelStatus()}</p>
                     </div>
                 </div>
                 <div class="row">
	                 <table class="table table-striped">
		                 <tr>
			                <th class="text-center">Created on</th>
			             	<th class="text-center">Photo</th>
			                <th class="text-center">Rating</th>
			                <th class="text-center">Trail Name</th>
			            </tr>
			            <c:forEach items="${reviews}" var="review" >
			                <tr>
			                    <td class="text-center align-middle"><c:out value="${review.getCreated()}" /></td>
			                   	<td class="text-center align-middle"><img src="data:image/jpg;base64,${review.getImage()}" width="100" height="100"/></td>
			                    <td class="text-center align-middle"><c:out value="${review.getRating()}" /></td>
			                    <td class="text-center align-middle"><c:out value="${review.getTrailName()}" /></td>
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
	

	<%-- <br/>
	<br/>
	<h1>User Information</h1>
	<h2>Hello ${firstName}!</h2>
        <table border="1">
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Phone Number</th>
                <th>Bio</th>
                <th>Address</th>
                <th>Level</th>
            </tr>
                <tr>
                    <td><c:out value="${result.getFirstName()}" /></td>
                    <td><c:out value="${result.getLastName()}" /></td>
                    <td><c:out value="${result.getEmail()}" /></td>
                    <td><c:out value="${result.getPhoneNumber()}" /></td>
                    <td><c:out value="${result.getBio()}" /></td>
                    <td><c:out value="${result.getAddress()}" /></td>
                    <td><c:out value="${result.getLevelStatus()}" /></td>
                </tr>
       </table>
       
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

    <span id="successMessage"><b>${messages.success}</b></span> --%>
</body>
</html>
