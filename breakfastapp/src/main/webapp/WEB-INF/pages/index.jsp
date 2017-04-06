<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home - Breakfast Service</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
    <link rel='stylesheet' href='css/login.css'>

    <script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/login.js"></script>
</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container"><center>
  <a class="navbar-brand"><h1>Breakfast Services</h1></a></center>
 <div class="navbar-header">
  <a class="label navbar-left">Welcome <c:choose><c:when test="${user eq null}">Guest</c:when> <c:otherwise>${user.name }</c:otherwise>  </c:choose>   </a>
   </div>
   
  </div>
  <br/><br/><br/><br/>
  <div>
  <c:choose>
  <c:when test="${user eq null}">
  <a class="navbar-link well-sm" href="/breakfastapp/login">Login</a> ||
  <a class="navbar-link well-sm" href="/breakfastapp/order-home">Order as guest user</a>
  </c:when>
  <c:otherwise>
  <a class="navbar-link well" href="/breakfastapp/logout">Logout</a> ||
  <a class="navbar-link well" href="/breakfastapp/order-home">Place Order</a>
  </c:otherwise>
  </c:choose>
  </div>
</nav>
<br/><br/><br/><br/><br/>
<div class="panel-info">
<p class="well"><h2>Surprise your husband, wife, father, mother or friend on their special day while still enjoying the comfort of your bed</h2></p>
</div>
</body>
</html>
