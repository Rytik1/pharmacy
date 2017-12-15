<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="localization.bundler"/>
<%@ taglib prefix="ctg" uri="customtags" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>game Coin</title>
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <link href="/resources/css/game.css" rel="stylesheet">
</head>
<body>
<c:import url="../section/header.jsp"></c:import>
<br>
<br>
<br>
<div class="container text-center">
    <fmt:message key="game.label.game" var="game"/>
    <h1 class=glow>${game}</h1>
</div>
<div class="container text-center">
    <fmt:message key="game.label.help" var="help"/>
    <h2 class=glow>${help}</h2>
</div>
<br>
<br>
<div class="container text-center">
    <div class="col-sm-12">
        <form name="game" method="POST" action="controler">
            <input type="hidden" name="command" value="game">
            <label>
                <input type="radio" name="checkCoin" value="0" id="Checkbox_Group1_0" required><img
                    src="../../resources/image/orel.png"
                    width="100" height="100"
                    alt="орел" style="background-color: inherit">
            </label>
            <label>
                <input type="radio" name="checkCoin" value="1" id="Checkbox_Group1_1"><img
                    src="../../resources/image/reshka.png"
                    width="100" height="100"
                    alt="решка" style="background-color: inherit">
            </label>
            <div class="container text-center">
                <fmt:message key="game.label.check" var="check"/>
                <input type="submit" value="${check}" class="button9"/>
            </div>
        </form>
    </div>
</div>
<div class="container text-center">
    <ctg:luckyTag/>
</div>
<div class="flip-container" style=" position: fixed;
    right: 430;
    bottom: 440px;" ontouchstart="this.classList.toggle('hover');">
    <div class="flipper">
        <div class="front">
            <img src="../../resources/image/222.jpg" width="150">
        </div>
        <c:if test="${ resultCoin eq '1' }">
            <div class="back">
                <img src="../../resources/image/reshka.png" alt="reshka" width="150" style="background-color: inherit">
            </div>
        </c:if>
        <c:if test="${ resultCoin eq '0' }">
            <div class="back">
                <img src="../../resources/image/orel.png" alt="orel" style="background-color: inherit" width="150">
            </div>
        </c:if>
    </div>
</div>
<c:import url="../section/footer.jsp"></c:import>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="/resources/js/bootstrap.js"></script>
</body>
</html>
