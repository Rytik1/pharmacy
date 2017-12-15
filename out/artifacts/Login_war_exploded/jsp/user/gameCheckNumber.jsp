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
    <title>game check number</title>
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <link href="/resources/css/game.css" rel="stylesheet">
</head>
<body>
<c:import url="../section/header.jsp"></c:import>
<br>
<br>
<br>
<div class="container text-center">
    <fmt:message key="gameCheckNumber.label.game" var="game"/>
    <h1 class="glow">${game}</h1>
</div>
<fmt:message key="gameCheckNumber.label.help" var="help"/>
<div class="container text-center">
    <marquee width="500"  ><h3>${help}</h3></marquee>
</div>
<br>
<br>
<div class="container text-center">
    <div class="col-sm-4"></div>
    <div class="col-sm-4">
        <fmt:message key="gameCheckNumber.label.empty" var="empty"/>
        <fmt:message key="gameCheckNumber.label.enter" var="enter"/>
        <div class="form-group">
            <label class="control-label col-xs-7" for="enter">${enter} </label>
            <div class="col-xs-5">
                <input id="enter" type="text">
            </div>
            <br>
            <fmt:message key="gameCheckNumber.label.counter" var="counter"/>
            <label for="enter" class="control-label col-xs-7">${counter} </label>
            <div class="col-xs-5">
                <input readonly id="counter" type="text" value="">
            </div>
            <div class="col-sm-4"></div>
        </div>
        <input hidden id="number" type="text" value="">
        <br>
        <fmt:message key="gameCheckNumber.button.check" var="check"/>
        <button style="margin-top: 30;" id="button1" class="button9" onclick=gameCheckNumber()>${check}</button>
    </div>
</div>
<div class="container text-center" id="form">
    <form name="basket" action="controler" method="post">
        <input type="hidden" name="command" value="GO_TO_BASKET">
        <input type="hidden" name="saleFromCheckNumber" value="0.9">
        <h3 id="message"></h3>
    </form>
</div>
<c:import url="../section/footer.jsp"></c:import>
<script src="/resources/js/gameCheckNumber.js"></script>
<script type="text/javascript" src="/resources/js/jquery-1.6.1.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="/resources/js/bootstrap.js"></script>
</body>
</html>
