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
    <title>Game sale</title>
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <link href="/resources/css/game.css" rel="stylesheet">
</head>
<body>
<c:import url="../section/header.jsp"></c:import>
<br>
<br>
<br>
<div class="container text-center">
    <fmt:message key="gameSale.label.gameSale" var="gameSale"/>
    <h1 class="glow">${gameSale}</h1>
</div>
<fmt:message key="gameSale.label.help" var="help"/>
<div class="container text-center">
    <marquee width="500" ><h3>${help}</h3></marquee>
</div>
<form name="basket" action="controler" method="post">
    <input type="hidden" name="command" value="GO_TO_BASKET">
    <input type="hidden" name="sales" value="0.95">
    <button id="button1"
            style=" border-radius: 197px;
            background-color: initial; position:absolute;" onmousemove="moveIt()"
    ><img src="../../resources/image/sales.jpg" width="250" height="250" alt="игра"></button>
</form>
<c:import url="../section/footer.jsp"></c:import>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="/resources/js/bootstrap.js"></script>
<script src="/resources/js/gameSales.js"></script>
</body>
</html>
