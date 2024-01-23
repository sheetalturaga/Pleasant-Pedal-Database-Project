<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a User Profile</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
</head>

<body>
	<section class="h-100 h-custom" style="background-color: #d6d6d7;">
	  <div class="container py-5 h-100">
	    <div class="row d-flex justify-content-center align-items-center h-100">
	      <div class="col-lg-8 col-xl-6">
	        <div class="card rounded-3">
	          <img src="resources/registrationpage.jpeg"
	            class="w-100" style="border-top-left-radius: .3rem; border-top-right-radius: .3rem;"
	            alt="Sample photo">
	          <div class="card-body p-4 p-md-5">
	            <h3 class="mb-4 pb-2 pb-md-0 mb-md-5 px-md-2">User Registration</h3>
	
		            <form class="px-md-2" action="createuser" method="post">
		
			              <div class="form-outline mb-4">
			                <input type="text" name="username" id="username" class="form-control" />
			                <label class="form-label" for="username">User Name</label>
			              </div>
			              <div class="form-outline mb-4">
			                <input type="text" name="password" id="password" class="form-control" />
			                <label class="form-label" for="password">Password</label>
			              </div>
			              <div class="form-outline mb-4">
			                <input type="text" name="firstname" id="firstname" class="form-control" />
			                <label class="form-label" for="firstname" >First Name</label>
			              </div>
			              <div class="form-outline mb-4">
			                <input type="text" name="lastname" id="lastname" class="form-control" />
			                <label class="form-label" for="lastname">Last Name</label>
			              </div>
			              <div class="form-outline mb-4">
			                <input type="text" name="bio" id="bio" class="form-control" />
			                <label class="form-label" for="bio">Bio</label>
			              </div>
			              <div class="form-outline mb-4">
			                <input type="text" name="email" id="email" class="form-control" />
			                <label class="form-label" for="email">Email</label>
			              </div>
			              <div class="form-outline mb-4">
			                <input type="text" name="address" id="address" class="form-control" />
			                <label class="form-label" for="address">Address</label>
			              </div>
			              
		                  <div class="d-md-flex justify-content-start align-items-center mb-4 ms-2 py-2">
		                  <h6 class="mb-0 me-auto">Level </h6>
		                  <div class="form-check form-check-inline mb-0 mx-auto me-4">
		                    <input class="form-check-input" type="radio" name="levelstatus" id="levelstatus"
		                      value="BEGINNER"/>
		                    <label class="form-check-label" for="level">Beginner</label>
		                  </div>
		
		                  <div class="form-check form-check-inline mb-0 me-4">
		                    <input class="form-check-input" type="radio" name="levelstatus" id="levelstatus"
		                      value="INTERMEDIATE" />
		                    <label class="form-check-label" for="level">Intermediate</label>
		                  </div>
		
		                  <div class="form-check form-check-inline mb-0">
		                    <input class="form-check-input" type="radio" name="levelstatus" id="levelstatus"
		                      value="ADVANCED" />
		                    <label class="form-check-label" for="level">Advanced</label>
		                  </div>
		                </div>
			            <button type="submit" class="btn btn-success btn-lg mb-1">Submit</button>
		            </form>
		            <p>
						<span id="successMessage"><b>${messages.success}</b></span>
					</p>
	          </div>
	        </div>
	      </div>
	    </div>
	  </div>
	</section>
</body>
</html>