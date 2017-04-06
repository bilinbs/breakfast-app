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
    <link rel='stylesheet' href='css/delivery-details.css'>

    <script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bfHome.js"></script>
<script type="text/javascript" src="js/delivery-details.js"></script>
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
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<div class="container">
 <table id="cart" class="table table-hover table-condensed">
        <thead>
      <tr>
       <th style="width:30%">Breakfast Set</th>
       <th style="width:8%">Price</th>
       <th style="width:30%">Serving Style</th>
       <th style="width:8%">Price</th>
       <th style="width:8%">Number of Persons</th>
       <th style="width:16%" class="text-center">Subtotal</th>
      </tr>
     </thead>
     <tbody>
      <tr>
       <td data-th="Product">
        <div class="row">
         <div class="col-sm-10">
          <h4 class="nomargin">${order.breakfastSets[0].name}</h4>
          <p>${order.breakfastSets[0].description}</p>
         </div>
        </div>
       </td>
       <td data-th="Price">Rs ${order.breakfastSets[0].price}</td>
       <td data-th="Product">
        <div class="row">
         <div class="col-sm-10">
          <h4 class="nomargin">${order.breakfastSets[0].servingStyle.name}</h4>
          <p>${order.breakfastSets[0].servingStyle.description}</p>
         </div>
        </div>
       </td>
       <td data-th="Price">Rs ${order.breakfastSets[0].servingStyle.price }</td>
       <td data-th="Quantity">
        <input id="noPerson" type="number" class="form-control text-center" value="1">
       </td>
       
       <td  data-th="Subtotal" class="text-center">Rs <strong id="subtotal">${order.totalPrice }</strong></td>
       <td class="actions" data-th="">
        <button class="btn btn-info btn-sm" onclick="updatePrice(${order.totalPrice })"><i class="fa fa-refresh"></i></button>
       </td>
      </tr>
     </tbody>
     <tfoot>
      <tr >
        <!-- panel preview -->
        <div class="col-sm-5">
            <h4>Delivery Details:</h4>
            <div class="panel panel-default">
                <div class="panel-body form-horizontal payment-form">
                    <div class="form-group">
                        <label for="paymentInfo" class="col-sm-3 control-label">Credit Card Number</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="paymentInfo" name="concept">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="daddress" class="col-sm-3 control-label">Delivery Address</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="daddress" name="description">
                        </div>
                    </div> 
                    <div class="form-group">
                        <label for="ddate" class="col-sm-3 control-label">Delivery Date</label>
                        <div class="col-sm-9">
                            <input type="date" class="form-control" id="ddate" name="date">
                        </div>
                    </div> 
                    <div class="form-group">
                        <label for="dtime" class="col-sm-3 control-label">Delivery Time</label>
                        <div class="col-sm-9">
                            <input type="time" class="form-control" id="dtime" name="amount">
                        </div>
                    </div>  
                </div>
            </div>            
        </div> <!-- / panel preview -->
      </tr>
      <tr>
       <td></td>
       <td colspan="4" class="hidden-xs"></td>
       <td class="hidden-xs text-center"></td>
       <td><a href="#" class="btn btn-success btn-block" onclick="checkout('confirmOrder')">Checkout <i class="fa fa-angle-right"></i></a></td>
      </tr>
     </tfoot>
    </table>
</div>
</body>
</html>