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
    <fmt:message key="administrator.label.deleteUser" var="deleteUser"/>

    <h1 class="glow">${deleteUser}</h1>
    </div>
<div class="container text-center">
    <div class="col-sm-2"></div>
    <div class="col-sm-8">

        <form method="post" action="controler" id="userDelete" class="delete">
            <input type="hidden" name="id" value="${ param.id }"/>
            <input type="hidden" name="role" value="${ param.role }"/>

            <input type="hidden" name="command" id="controllerCommand" value="DELETE_USER">

            <div class="form-group">
                <fmt:message key="delete.label.login" var="login"/>

                <label class="control-label">${login}: </label>
                ${ param.login }
            </div>

            <div class="form-group">
                <fmt:message key="delete.label.gender" var="gender"/>

                <label class="control-label">${gender}:</label>
                ${ param.gender } 
            </div>
            <div class="form-group">
                <fmt:message key="delete.label.email" var="email"/>

                <label class="control-label">${email}:</label>
                ${ param.email } 
            </div>


            <div class="form-group">
                <fmt:message key="delete.label.names" var="name"/>

                <label for="medicine-country" class="control-label">${name}:</label>
                ${ param.name } 
            </div>

            <div class="form-group">
                <fmt:message key="delete.label.surname" var="surname"/>

                <label class="control-label">${surname}:</label>
                ${ param.surname } 
            </div>
            <div class="form-group">
                <fmt:message key="delete.label.numberReceipt" var="numberReceipt"/>

                <label class="control-label">${numberReceipt}:</label>
                ${ param.numberReceipt } 
            </div>
            <div class="form-group">
                <fmt:message key="delete.label.role" var="role"/>

                <label class="control-label">${role}:</label>
                ${ param.role } 
            </div>
            <div class="form-group">
                <fmt:message key="delete.label.city" var="city"/>

                <label class="control-label">${city}:</label>
                ${ param.city } 
            </div>

        </form>
        
        </div>
        <div class="col-sm-2"></div>
    <div class="modal-footer">
               <div class="col-xs-offset-3 col-xs-9">

            <fmt:message key="delete.label.deletebutton" var="deletebutton"/>

            <button type="submit" class="btn btn-danger" form="userDelete" id="confirm">${deletebutton}</button>
        </div>
    </div>
    <span class="error"> ${cantDeleteAdmin}</span>

    <span class="good"> ${resultdelete}</span>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="resources/js/bootstrap.js"></script>

</body>
</html>

