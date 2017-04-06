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
    <link rel='stylesheet' href='css/bfHome.css'>
    <link rel='stylesheet' href='css/editItems.css'>

    <script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bfHome.js"></script>
<script type="text/javascript" src="js/editItems.js"></script>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
 <div class="navbar-header">
  <a class="navbar-brand">Breakfast Service</a>
   </div>
   <a class="label">Welcome <c:choose><c:when test="${user eq null}">Guest</c:when> <c:otherwise>${user.name }</c:otherwise>  </c:choose>   </a>
   
  </div>
</nav>
<br/><br/><br/><br/><br/><br/><br/><br/>
    <div class="container">
    <div class="jumbotron">Customize Items in your chosen set</div>
      <c:forEach items="${items }" var="item">
            <div class="row">
          <div class="col-xs-3 col-xs-offset-3">
          <p>${item.name }</p>
          <p>${item.description }</p>
          <p>Price per ${item.servingUnit}: ${item.unitPrice }</p>
           <div class="input-group number-spinner">
            <span class="input-group-btn data-dwn">
             <button class="btn btn-default btn-info" data-dir="dwn"><span class="glyphicon glyphicon-minus"></span></button>
            </span>
            <input id="${item.id }" type="text" class="form-control text-center" value=<c:choose><c:when test="${not empty bfSet.items[item] }">${bfSet.items[item] }</c:when>
                  <c:otherwise> 0</c:otherwise> </c:choose> min="0" max="100">
            <span class="input-group-btn data-up">
             <button class="btn btn-default btn-info" data-dir="up"><span class="glyphicon glyphicon-plus"></span></button>
            </span>
           </div>
          </div>
         </div>
         <hr/>
      </c:forEach>
      <div class="well center">
      <button class="btn-primary text-center" onclick="confirmOrder()">Confirm Order</button>
      </div>
    </div>
    
    <br/><br/><br/><br/>
</body>
</html>