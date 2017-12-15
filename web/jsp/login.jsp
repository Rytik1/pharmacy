<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="localization.bundler"/>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login</title>
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <link href="/resources/css/login.css" rel="stylesheet">
</head>
<body>
<form>
    <select id="language" name="language" onchange="submit()" class="selectpicker" data-style="btn-primary">
        <option value="en_US" ${language == "en_US" ? "selected" : ""}>English</option>
        <option value="ru_RU" ${language == "ru_RU" ? "selected" : ""}>Русский</option>
    </select>
</form>
<div class="container text-center">
    <br>
    <br>
    <br>
    <br>
    <br>
    <fmt:message key="login.label.help" var="help"/>
    <h1 class=glow>${help}</h1>
</div>
<br>
<section class="intro"></section>
<div class="container text-center">
    <div class="col-sm-12">
        <form name="loginForm" method="POST" action="/controler">
            <input type="hidden" name="command" value="login"/>
            <fmt:message key="login.label.login" var="login"/>
            <label for="login"> ${login} </label>
            <br>
            <input type="text" name="login" id="login" value="" placeholder="${login}" required/>
            <br>
            <fmt:message key="login.label.password" var="password"/>
            <label for="password"> ${password} </label>
            <br>
            <input type="password" id="password" name="password" value="" placeholder="${password}" required/>
            <br>
            <span class="error">${errorLoginPassMessage} ${wrongAction} ${nullPage}</span>
            <br>
            <fmt:message key="login.button.submit" var="sigIn"/>
            <input type="submit" value="${sigIn}" class="btn btn-primary"/>
            <br>
        </form>
        <form name="loginForm" method="POST" action="/controler">
            <input type="hidden" name="command" value="GO_TO_REGISTRATION"/>
            <fmt:message key="login.button.registration" var="registration"/>
            <input type="submit" value="${registration}" class="btn btn-primary"/>
        </form>
    </div>
</div>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="/resources/js/bootstrap.js"></script>
</body>
</html>
