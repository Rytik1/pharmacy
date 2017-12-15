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
    <title>header</title>
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <link href="/resources/css/basket.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <img src="/resources/image/logo.jpg" width="70%">
        </div>
        <ul class="nav navbar-nav">
            <c:if test="${role=='USER'}">
                <fmt:message key="main.label.home" var="home"/>
                <li class="active"><a class="button9" href="controler?command=HOME">${home}</a></li>
            </c:if>
            <c:if test="${role=='ADMIN'}">
                <fmt:message key="main.label.home" var="home"/>
                <li class="active"><a class="button9" href="controler?command=HOME_ADMINISTRATOR">${home}</a></li>
            </c:if>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li>
                <form name="logout" method="POST" action="controler">
                    <input type="hidden" id="logout" name="command" value="logout"/>
                    <fmt:message key="main.label.USER" var="USER"/>
                    <fmt:message key="signout.button.submit" var="Logout"/>
                    <label>${USER}${user}</label>
                    <fmt:message key="signout.button.submit" var="Logout"/>
                    <input class="button9" type="submit" value="${Logout}"/>
                </form>
            </li>
        </ul>
    </div>
</nav>
</body>
</html>
