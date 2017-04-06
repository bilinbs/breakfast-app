<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Breakfast Set- Breakfast Service</title>
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
<c:choose>
<c:when test="${not empty bfset }"><p class="label label-success">Created breakfast set <strong> ${bfset.name} </strong>with id ${bfset.id } </p>  </c:when>
<c:otherwise>
 <div class="row">
  <h2>Add a new Breakfast Set</h2>
 </div>
    
    <div class="row">
        <div class="col-sm-offset-4 col-sm-4">
            <form:form method="post" modelAttribute="itemQtyListDto" action="/breakfastapp/addBFSet">
    <div class="form-group">
           <label for="bfset-name">Breakfast Set Name</label>
     <div class="input-group">
      <input type="text" class="form-control" name="bfset-name" id="bfset-name" placeholder="Breakfast Set Name" required>
      <span class="input-group-addon danger"><span class="glyphicon glyphicon-remove"></span></span>
     </div>
    </div>

        <div class="form-group">
           <label for="bfset-desc">Breakfast Set Description</label>
     <div class="input-group">
      <input type="text" class="form-control" name="bfset-desc" id="bfset-desc" placeholder="Breakfast Set Description" required>
      <span class="input-group-addon danger"><span class="glyphicon glyphicon-remove"></span></span>
     </div>
    </div>
    <div class="form-group">
           <label for="bfset-price">Price</label>
     <div class="input-group" data-validate="number">
      <input type="text"  class="form-control" name="bfset-price" id="bfset-price" placeholder="Price" required>
      <span class="input-group-addon danger"><span class="glyphicon glyphicon-remove"></span></span>
     </div>
    </div>
    <div class="well">Enter quantities for items</div>
    <c:forEach items="${itemQtyListDto.itemQtyList }" var="itemQty" varStatus="status">
             <div class="form-group">
           <label for="itemQtyList[${status.index}].qty">${itemQty.name }</label>
     <div class="input-group" data-validate="number">
      <input type="hidden" class="form-control" name="itemQtyList[${status.index}].id" id="itemQtyList[${status.index}].id" placeholder="Quantity" value="${itemQty.id }">
      <input type="text"  class="form-control" name="itemQtyList[${status.index}].qty" id="itemQtyList[${status.index}].qty" placeholder="Quantity" value="${itemQty.qty }" required>
      <span class="input-group-addon danger"><span class="glyphicon glyphicon-remove"></span></span>
     </div>
    </div>
    </c:forEach>
    <div class="form-group">
           <label for="bfset-price">Serving styles in which this set can be served</label>
     <div class="input-group" data-validate="number">
      <select multiple  class="form-control" name="bfset-sstyles" id="bfset-sstyles"  required>
      <c:forEach items="${sstyles }" var="sstyle">
      <option value="${sstyle.id }">${sstyle.name }</option>
    </c:forEach>
      </select>
     </div>
    </div>
    

                <button type="submit" class="btn btn-primary col-xs-12" >Submit</button>
            </form:form>
        </div>
    </div>
    </c:otherwise>
    </c:choose>
</div>
</body>
</html>