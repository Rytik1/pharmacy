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
    <link href="resources/css/game.css" rel="stylesheet">
</head>
<body>

    
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
                           <img src="../resources//image/logo.jpg"  width="70%" >

        </div>
        <ul class="nav navbar-nav">
            <fmt:message key="main.label.home" var="home"/>
            <li class="active"><a href="controler?command=HOME">${home}</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li>
                <form name="logout" method="POST" action="controler">
                    <input type="hidden" id="logout" name="command" value="logout"/>
                    <fmt:message key="main.label.USER" var="USER"/>
                    <fmt:message key="signout.button.submit" var="Logout"/>
                    <label for="submit" >${USER}${user}</label>
                    <fmt:message key="signout.button.submit" var="Logout"/>
                    <input type="submit" value="${Logout}"/>
                </form>
            </li>
        </ul>
    </div>
</nav>

<br>
<br>
<br>
<div class="container text-center">
    <fmt:message key="game.label.game" var="game"/>

    <h1 class="glow">${game}</h1>
        </div>
    
    <div class="container text-center">
    <fmt:message key="game.label.help" var="help"/>

    <h3 class="glow">${help}</h3>
        </div>
    <br>
    <br>
    
<div class="container text-center">
    <div class="col-sm-12">

        <form method="POST" action="controler">
            <input type="hidden" name="command" value="game">
            <label>

                <input type="radio" name="lucky" value="0" id="Checkbox_Group1_0"><img src="../resources/image/6.jpg"
                                                                                       width="100" height="111"
                                                                                       alt="орел">

            </label>
            <label>
                <input type="radio" name="lucky" value="1" id="Checkbox_Group1_1"><img src="../resources/image/7.jpg"                                                                                      width="100" height="111"
                                                                                   alt="решка">
            </label>
               <div class="container text-center">
             <fmt:message key="game.label.check" var="check"/>

            <input type="submit" value="${check}" class="btn btn-primary"/>        
    </div>
        </form>

    </div>
</div>
    <br>
    
  <div class="container text-center">
<ctg:luckyTag/>
    </div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="resources/js/bootstrap.js"></script>

</body>
</html>
