<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="localization.bundler"/>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Delete user</title>
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <link href="/resources/css/registration.css" rel="stylesheet">
</head>
<body>
<c:import url="../section/header.jsp"></c:import>
<br>
<br>
<br>
<div class="container text-center">
    <h3 class="good" float=center> ${resultdelete} </h3>
    <h3 class="error"
        float=center> ${cantDeleteAdmin}</h3>
</div>
<div class="container text-center">
    <fmt:message key="administrator.label.deleteUser" var="deleteUser"/>
    <h1 class="glow">${deleteUser}</h1>
</div>
<div class="container text-center">
    <div class="col-sm-2"></div>
    <div class="col-sm-8">
        <form name="delete user" method="post" action="controler" id="userDelete" class="delete">
            <input type="hidden" name="id" value="${ param.id }"/>
            <input type="hidden" name="role" value="${ param.role }"/>
            <input type="hidden" name="command" id="controllerCommand" value="DELETE_USER">
            <div class="form-group">
                <fmt:message key="delete.label.login" var="login"/>
                <h4 class="glow"> ${login}: ${ param.login }</h4>
            </div>
            <div class="form-group">
                <fmt:message key="delete.label.gender" var="gender"/>
                <h4 class="glow"> ${gender}: ${ param.gender } </h4>
            </div>
            <div class="form-group">
                <fmt:message key="delete.label.email" var="email"/>
                <h4 class="glow"> ${email}: ${ param.email }</h4>
            </div>
            <div class="form-group">
                <fmt:message key="delete.label.names" var="name"/>
                <h4 class="glow"> ${name}: ${ param.name } </h4>
            </div>
            <div class="form-group">
                <fmt:message key="delete.label.surname" var="surname"/>
                <h4 class="glow"> ${surname}: ${ param.surname } </h4>
            </div>
            <div class="form-group">
                <fmt:message key="delete.label.numberReceipt" var="numberReceipt"/>
                <h4 class="glow">  ${numberReceipt}: ${ param.numberReceipt } </h4>
            </div>
            <div class="form-group">
                <fmt:message key="delete.label.role" var="role"/>
                <h4 class="glow"> ${role}: ${ param.role } </h4>
            </div>
            <div class="form-group">
                <fmt:message key="delete.label.city" var="city"/>
                <h4 class="glow"> ${city}: ${ param.city } </h4>
            </div>
            <div class="form-group">
                <fmt:message key="delete.label.deletebutton" var="deletebutton"/>
                <button type="submit" class="btn btn-danger" form="userDelete" id="confirm">${deletebutton}</button>
            </div>
        </form>
    </div>
    <div class="col-sm-2"></div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="/resources/js/bootstrap.js"></script>
</body>
</html>

