<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
    <link rel='stylesheet' href='css/login.css'>

    
</head>
<body>
     
    <nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
 <div class="navbar-header">
  <a class="navbar-brand">Breakfast Service</a>
 </div>
  </div>
</nav>
<script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<!-- Where all the magic happens -->
<!-- LOGIN FORM -->
<div class="text-center" style="padding:50px 0">
 <div class="logo">login</div>
 <!-- Main Form -->
 <div class="login-form-1">
  <form id="login-form" class="text-left" action="login" method="POST">
   <div class="login-form-main-message"></div>
   <div class="main-login-form">
    <div class="login-group">
     <div class="form-group">
      <label for="lg_username" class="sr-only">Username</label>
      <input type="text" class="form-control" id="username" name="username" placeholder="username">
     </div>
     <div class="form-group">
      <label for="lg_password" class="sr-only">Password</label>
      <input type="password" class="form-control" id="password" name="password" placeholder="password">
     </div>
    </div>
    <button type="submit" class="login-button"><i class="fa fa-chevron-right">Login</i></button>
   </div>
   <c:if test="${not empty errorMsg}">
   <div class="alert alert-danger">
  <strong>${errorMsg }</strong> 
</div>
</c:if>
  </form>
 </div>
 <!-- end:Main Form -->
</div>
</body>
</html>


