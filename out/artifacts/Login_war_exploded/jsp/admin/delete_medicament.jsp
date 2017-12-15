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
    <title>Delete medicament</title>
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <link href="/resources/css/registration.css" rel="stylesheet">
</head>
<body>
<c:import url="../section/header.jsp"></c:import>
<br>
<br>
<br>
<div class="container text-center">
    <h3 class="good" float=center> ${resultdelete} ${name}</h3>
</div>
<div class="container text-center">
    <fmt:message key="administrator.label.deleteMedicament" var="deleteMedicament"/>
    <h1 class="glow">${deleteMedicament}</h1>
</div>
<div class="container text-center">
    <div class="col-sm-2"></div>
    <div class="col-sm-8">
        <form name="delete medicament" method="post" action="controler" id="medicineDelete">
            <input type="hidden" name="id" value="${ param.id }"/>
            <input type="hidden" name="name" value="${ param.name }"/>
            <input type="hidden" name="command" id="controllerCommand" value="DELETE_MEDICAMENT">
            <div class="form-group">
                <fmt:message key="delete.label.name" var="name"/>
                <h4 class="glow">${name}: ${ param.name }</h4>
            </div>
            <div class="form-group">
                <fmt:message key="delete.label.dosage" var="dosage"/>
               <h4 class="glow">${dosage}: ${ param.dosage }</h4>
            </div>
            <div class="form-group">
                <fmt:message key="delete.label.country" var="country"/>
                <h4 class="glow">${country}: ${ param.country }</h4>
            </div>
            <div class="form-group">
                <fmt:message key="delete.label.recipe" var="recipe"/>
                <h4 class="glow">${recipe}: ${param.recipe}
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
                </h4>
            </div>
            <div class="form-group">
                <fmt:message key="delete.label.price" var="price"/>
                <h4 class="glow">${price}: ${ param.price }</h4>
            </div>
            <div class="form-group">
                <fmt:message key="delete.label.count" var="count"/>
                <h4 class="glow">${count}: ${ param.count }</h4>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-danger" form="medicineDelete" id="confirm">удалить</button>
            </div>
        </form>
        <div class="col-sm-2"></div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="/resources/js/bootstrap.js"></script>
</body>
</html>

        