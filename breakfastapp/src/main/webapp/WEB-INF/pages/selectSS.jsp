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

    <script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bfHome.js"></script>
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

<div class="container">
	<div class="row">
        <h2></h2>
		<h2>You have selected the breakfast set ${bfSet.name }. Now select a serving style for the breakfast.</h2>
    </div>
    <div class="row">
        <div class="form-group">
            <div class="searchable-container">
              <c:forEach items="${sstyles}" var="sstyle" >
                   <div class="items col-xs-5 col-sm-5 col-md-3 col-lg-3">
                       <div class="info-block block-info clearfaix">
                           <div class="square-box pull-left">
                               <span class="glyphicon glyphicon-tags glyphicon-lg"></span>
                           </div>
                           <div data-toggle="buttons" class="btn-group bizmoduleselect" onclick="selectServStyle(${sstyle.id},'${sstyle.name }')">
                               <label class="btn btn-default">
                                   <div class="bizcontent">
                                       <span class="glyphicon glyphicon-ok glyphicon-lg"></span>
                                       <strong>${sstyle.name }</strong>
                                       <p class="text-center" style="white-space:normal">${sstyle.description }</p>
                                       <p>Price : Rs ${sstyle.price }</p>
                                   </div>
                               </label>
                           </div>
                       </div>
                   </div>
                 </c:forEach>
            </div>
        </div>
	</div>
</div>


</body>
</html>