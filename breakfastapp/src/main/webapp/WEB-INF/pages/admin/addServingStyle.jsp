<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Serving Style - Breakfast Service</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
    <link rel='stylesheet' href='css/addItem.css'>

    <script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/input.js"></script>
</head>
<body>


<div class="container">
<a class="text-primary" href="/breakfastapp/adminHome"><span class="glyphicon glyphicon-circle-arrow-left"></span> Back to home </a>
<c:if test="${not empty sstyle }"><p class="label label-success">Created serving style <strong> ${sstyle.name} </strong>with id ${sstyle.id } </p>  </c:if>
 <div class="row">
  <h2>Add a new Serving Style</h2>
 </div>
    
    <div class="row">
        <div class="col-sm-offset-4 col-sm-4">
            <form method="post" action="/breakfastapp/addServingStyle">
    <div class="form-group">
           <label for="sstyle-name">Serving Style Name</label>
     <div class="input-group">
      <input type="text" class="form-control" name="sstyle-name" id="sstyle-name" placeholder="Serving Style Name" required>
      <span class="input-group-addon danger"><span class="glyphicon glyphicon-remove"></span></span>
     </div>
    </div>

        <div class="form-group">
           <label for="sstyle-desc">Serving Style Description</label>
     <div class="input-group">
      <input type="text" class="form-control" name="sstyle-desc" id="sstyle-desc" placeholder="Serving Style Description" required>
      <span class="input-group-addon danger"><span class="glyphicon glyphicon-remove"></span></span>
     </div>
    </div>
    
             <div class="form-group">
           <label for="sstyle-price">Price per unit</label>
     <div class="input-group" data-validate="number">
      <input type="text"  class="form-control" name="sstyle-price" id="sstyle-price" placeholder="Price" required>
      <span class="input-group-addon danger"><span class="glyphicon glyphicon-remove"></span></span>
     </div>
    </div>
                <button type="submit" class="btn btn-primary col-xs-12" >Submit</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>