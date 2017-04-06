<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Item - Breakfast Service</title>
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
<c:if test="${not empty item }"><p class="label label-success">Created item <strong>${item.name} </strong>with id ${item.id } </p>  </c:if>
 <div class="row">
  <h2>Add a new item</h2>
 </div>
    
    <div class="row">
        <div class="col-sm-offset-4 col-sm-4">
            <form method="post" action="/breakfastapp/addItem">
    <div class="form-group">
           <label for="item-name">Item Name</label>
     <div class="input-group">
      <input type="text" class="form-control" name="item-name" id="item-name" placeholder="Item Name" required>
      <span class="input-group-addon danger"><span class="glyphicon glyphicon-remove"></span></span>
     </div>
    </div>

        <div class="form-group">
           <label for="item-desc">Item Description</label>
     <div class="input-group">
      <input type="text" class="form-control" name="item-desc" id="item-desc" placeholder="Item Description" required>
      <span class="input-group-addon danger"><span class="glyphicon glyphicon-remove"></span></span>
     </div>
    </div>
    
<div class="form-group">
           <label for="serving-unit">Served as units of</label>
     <div class="input-group">
      <input type="text" min="0" class="form-control" name="serving-unit" id="serving-unit" placeholder="Serving Units" list="sunitlist" required>
      <datalist id="sunitlist">
      <c:forEach items="${servingUnits }" var="sunit"><option value="${sunit }"></c:forEach>
      </datalist>
      <span class="input-group-addon danger"><span class="glyphicon glyphicon-remove"></span></span>
     </div>
    </div>
             <div class="form-group">
           <label for="unit-price">Price per unit</label>
     <div class="input-group" data-validate="number">
      <input type="text"  class="form-control" name="unit-price" id="unit-price" placeholder="Unit Price" required>
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