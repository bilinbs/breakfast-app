<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin - Breakfast Service</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
    <link rel='stylesheet' href='css/adminHome.css'>

    <script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/login.js"></script>
</head>
<body>
      <ul class="nav navbar-nav">
       <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">${user.name }<span class="glyphicon glyphicon-user pull-right"></span></a>
          <ul class="dropdown-menu">
            <li><a href="/breakfastapp/logout">Log Out <span class="glyphicon glyphicon-log-out pull-right"></span></a></li>
          </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Add<span class="glyphicon glyphicon-record pull-right"></span></a>
          <ul class="dropdown-menu">
            <li><a href="/breakfastapp/addItem">Add Item </a></li>
            <li class="divider"></li>
            <li><a href="/breakfastapp/addServingStyle">Add Serving Style </a></li>
            <li class="divider"></li>
            <li><a href="/breakfastapp/addBFSet">Add Breakfast Set Template</a></li>
            <li class="divider"></li>
          </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Modify<span class="glyphicon glyphicon-edit pull-right"></span></a>
          <ul class="dropdown-menu">
            <li><a href="/breakfastapp/addItem">Modify Item </a></li>
            <li class="divider"></li>
            <li><a href="#">Modify Serving Style </a></li>
            <li class="divider"></li>
            <li><a href="#">Modify Breakfast Set Template</a></li>
            <li class="divider"></li>
            <li><a href="#">Modify Orders </a></li>
            <li class="divider"></li>
          </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">View<span class="glyphicon glyphicon glyphicon-search pull-right"></span></a>
          <ul class="dropdown-menu">
            <li><a href="/breakfastapp/addItem">View All Items </a></li>
            <li class="divider"></li>
            <li><a href="#">View All Serving Styles </a></li>
            <li class="divider"></li>
            <li><a href="#">View All Breakfast Set Template</a></li>
            <li class="divider"></li>
            <li><a href="#">View All Orders </a></li>
            <li class="divider"></li>
          </ul>
        </li>
      </ul>
</body>
</html>