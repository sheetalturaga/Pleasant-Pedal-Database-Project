<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link href="${contextPath}/bootstrap.min.css" rel="stylesheet">
<link href="pleasantpeddle/src/main/webapp/bootstrap/css/bootstrap.min/bootstrap.min.css" rel="stylesheet">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<section class="h-100 gradient-form" style="background-color: #d6d6d7;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-xl-10">
                <div class="card rounded-3 text-black">
                    <div class="row g-0" style="height: 50%">
                        <div class="col-lg-6 bg-white">
                            <div class="card-body p-md-5 mx-md-4">
                            <br>
                                <div class="has-bg-img bg-white bg-blend-multiply text-center">
                                    <img class="bg-image" src="resources/rainbowlog-whitebg-modified.png"
                                         style="width: 300px;" alt="logo">
                                </div>
                                <br></br>
                                <form action="login" method="post">
                                    <p>Please login to your account</p>

                                    <div class="form-outline mb-4">
                                        <input id="username" class="form-control" name ="username"
                                               placeholder="Username" />
                                        <label class="form-label" for="username">UserName</label>
                                    </div>

                                    <div class="form-outline mb-4">
                                        <input type="password" id="password" class="form-control" name ="password"
                                        placeholder="Password"/>
                                        <label class="form-label" for="password">Password</label>
                                    </div>
                                    

                                    <div class="text-center pt-1 mb-5 pb-1">
                                        <button id="login" name="nextstep" class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3" type="submit" value="login">Log
                                            in</button>
                                        <a class="text-muted" href="#!">Forgot password?</a>
                                    </div>

                                    <div class="d-flex align-items-center justify-content-center pb-4">
                                        <p class="mb-0 me-2 px-2">Don't have an account? </p>

                                        <button id="create" name="nextstep" type="submit" class="btn btn-outline-danger" value="create">New Profile</button>
                                    </div>

                                </form>
								<p>
									<span id="successMessage" class="text-danger"><b>${messages.success}</b></span>
								</p>
                            </div>
                        </div>
                        <div class="col-lg-6 d-flex align-items-center #aaa">
                            <div class="text-secondary px-3 py-4 p-md-5 mx-md-4">
                                <h4 class="mb-4">More than just trails..</h4>
                                <p class="medium mb-0">No matter where you place yourself in the experience chart, we have something for everyone. 
                                Log in now to avail all our features to best plan your ride.</p>
                            	<p class="medium mb-0 py-4 font-weight-bold">HAPPY TRAILS!!</p>
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