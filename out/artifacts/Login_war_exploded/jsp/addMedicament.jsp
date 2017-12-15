<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="localization.bundler"/>
<%@ taglib prefix="ctg" uri="customtags" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Title</title>
    <link href="resources/css/bootstrap.css" rel="stylesheet">
    <link href="resources/css/registration.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
                  <img src="../resources//image/logo.jpg"  width="70%" >

        </div>
        <ul class="nav navbar-nav">
            <fmt:message key="main.label.home" var="home"/>
            <li class="active"><a href="controler?command=HOME_ADMINISTRATOR">${home}</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li>
                <form name="logout" method="POST" action="controler">
                    <input type="hidden" id="logout" name="command" value="logout"/>
                    <fmt:message key="main.label.USER" var="USER"/>
                    <fmt:message key="signout.button.submit" var="Logout"/>
                    <label for="submit">${USER}${user}</label>
                    <fmt:message key="signout.button.submit" var="Logout"/>
                    <input id="submit" type="submit" value="${Logout}"/>
                </form>
            </li>
        </ul>
    </div>
</nav>

<br>
<br>
<br>
    <div class="container text-center">
    <fmt:message key="administrator.label.addMed" var="addMed"/>

    <h1 class="glow">${addMed}</h1>
    </div>
<div class="container text-center">
    <div class="col-sm-2"></div>
    <div class="col-sm-8">
        <form class="form-horizontal"  method="post" action="controler" id="medicineAdd">
            <input type="hidden" name="command" id="controllerCommand" value="ADD_MEDICAMENT">
           
            <div class="form-group">
                <fmt:message key="administrator.label.name" var="names"/>
                <label for="medicine-name" class="control-label col-xs-3">${names} </label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" name="name" id="medicine-name" required placeholder="${names} "
                           pattern="[a-zA-Z1-9 ]{1,20}">
                </div>
            </div>
      
            <div class="form-group">
                <fmt:message key="administrator.label.dosage" var="dosage"/>
                <label for="medicine-medicineDosage" class="control-label col-xs-3">${dosage}:</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" name="dosage" id="medicine-medicineDosage" required placeholder="${dosage}" >
                </div>
            </div>
            <div class="form-group">
                <fmt:message key="administrator.label.country" var="country"/>
                <label for="medicine-country" class="control-label col-xs-3">${country}</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" name="country" id="medicine-country" required placeholder="${country} ">
                </div>
            </div>
            
            <div class="form-group">
                <fmt:message key="administrator.label.recipe" var="recipe"/>
                <label class="control-label col-xs-3">${recipe}</label>
                <div class="col-xs-9">
                    <label class="radio-inline">
                        <fmt:message key="administrator.label.yes" var="yes"/>
                        <input type="radio" class="medicine-recipe" name="recipe_required"
                               value="true" id="yes"  required> ${yes}   
                    </label>
                    <label class="radio-inline">
                        <fmt:message key="administrator.label.no" var="no"/>
                        <input type="radio" class="medicine-recipe" name="recipe_required" value="false"
                               id="no"> ${no} 
                    </label>
                </div>
            </div>
            <div class="form-group">
                <fmt:message key="administrator.label.price" var="price"/>
                <label for="medicine-price" class="control-label col-xs-3">${price}</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" name="price" id="medicine-price" required
                           pattern="^[0-9]{1,7}[.]?[0-9]{2}"  placeholder="${price}">
                    <fmt:message key="administrator.label.priceHelp" var="priceHelp"/>
                    <div class="help-block">${priceHelp}</div>
                </div>
            </div>
            
            
             
            
            
            <div class="form-group">
                <fmt:message key="administrator.label.count" var="count"/>
                <label for="medicine-amount" class="control-label col-xs-3">${count}</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" name="amount_in_stock" id="medicine-amount" required  placeholder="${count}"
                           pattern="^[0-9]{1,7}[.]?[0-9]{0,3}" >
                    <fmt:message key="administrator.label.countHelp" var="countHelp"/>
                    <div class="help-block">${countHelp}</div>

                </div>
            </div>
        </form>
        <br>
        
    </div>
    
    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">

            <fmt:message key="administrator.label.add" var="add"/>
            <button type="submit" class="btn btn-primary" form="medicineAdd" id="confirm">${add}</button>
        </div>
       
    </div>

    
    <div class="col-sm-2"></div>
</div>
  <span class="error">  ${countwrong}${pricewrong} ${countrywrong}  </span>
    <span class="good">${NewMedicament} ${name}  </span>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="resources/js/bootstrap.js"></script>

</body>
</html>
