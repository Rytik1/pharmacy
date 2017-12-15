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
    <fmt:message key="administrator.label.deleteMedicament" var="deleteMedicament"/>

    <h1 class="glow">${deleteMedicament}</h1>
    </div>
<div class="container text-center">
    <div class="col-sm-2"></div>
    <div class="col-sm-8">

        <form method="post" action="controler" id="medicineDelete">
            <input type="hidden" name="id" value="${ param.id }"/>
            <input type="hidden" name="name" value="${ param.name }"/>

            <input type="hidden" name="command" id="controllerCommand" value="DELETE_MEDICAMENT">
            <div class="form-group">
                <fmt:message key="delete.label.name" var="deleteMedicament"/>

                <label class="control-label">name </label>
                ${ param.name }
            </div>

            <div class="form-group">
                <fmt:message key="delete.label.dosage" var="dosage"/>

                <label class="control-label">${dosage}:</label>
                ${ param.dosage }
            </div>
            <div class="form-group">
                <fmt:message key="delete.label.country" var="country"/>

                <label class="control-label">${country}</label>
                ${ param.country }
            </div>
            <div class="form-group">
                <fmt:message key="delete.label.recipe" var="recipe"/>

                <label class="control-label">${recipe}</label>
                ${param.recipe}
                <c:set var="isResipe" value="${param.recipe} }"/>
                <c:choose>
                    <c:when test="${ isResipe eq 'true' }">
                        <fmt:message key="delete.label.yes" var="yes"/>
                        <c:out value="${yes}"/>
                    </c:when>
                    <c:otherwise>
                        <fmt:message key="delete.label.no" var="no"/>
                        <c:out value="${no}"/>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="form-group">
                <fmt:message key="delete.label.price" var="price"/>

                <label class="control-label">${price}</label>
                ${ param.price }
            </div>
            <div class="form-group">
                <fmt:message key="delete.label.count" var="count"/>

                <label class="control-label">${count}</label>
                ${ param.count }
            </div>

          </form>  
        <div class="col-sm-2"></div>
     <div class="modal-footer">
                <button type="submit" class="btn btn-danger" form="medicineDelete" id="confirm">удалить</button>
            </div>
    </div>
   

    
    
    <span class="good">   ${resultdelete} ${name}</span>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="resources/js/bootstrap.js"></script>
</body>
</html>

        