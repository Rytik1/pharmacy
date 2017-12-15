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
            <li class="active"><a href="controler?command=HOME">${home}</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li>
                <form name="logout" method="POST" action="controler">
                    <input type="hidden" id="logout" name="command" value="logout"/>
                    <fmt:message key="main.label.USER" var="USER"/>
                    <fmt:message key="signout.button.submit" var="Logout"/>
                    <label for="submit">${USER}${user}</label>
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
    <div class="col-sm-2"></div>
    <div class="col-sm-8">
        <form class="form-horizontal" action="controler" method="post">
            <input type="hidden" name="command" value="UPDATE_USER"/>
            <input type="hidden" name="login" value=${users.login}/>
            <input type="hidden" name="email" value=${users.email}/>
            <div class="form-group">
                <fmt:message key="update.label.login" var="login"/>
                <label class="control-label col-xs-3" for="login"> ${login} :</label>
                <div class="col-xs-9">
                    <input type="text" value="${users.login}" readonly id="login" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <fmt:message key="update.label.email" var="email"/>
                <label class="control-label col-xs-3" for="email"> ${email} :</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" value="${users.email}" readonly id="email"/>
                </div>
            </div>
            <div class="form-group">
                <fmt:message key="update.label.surname" var="surname"/>
                <label class="control-label col-xs-3" for="surname"> ${surname} :</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" name="surname" id="surname" placeholder=" "
                           value="${users.surname}" required pattern="^[a-zA-Zа-яА-ЯёЁ]*$">
                </div>
            </div>
            <div class="form-group">
                <fmt:message key="update.label.name" var="name"/>
                <label class="control-label col-xs-3" for="name">${name}:</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" name="name" id="name" placeholder="" value="${users.name}"
                           required pattern="^[a-zA-Zа-яА-ЯёЁ]*$">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-xs-3 form-control-label" for="birth">
                    <fmt:message key="update.label.birthdate" var="birthdate"/>
                    ${birthdate}
                </label>
                <div class="col-xs-9 date">
                    <div class="input-group date" id="birth">
                        <input type="date" id="birthDate" name="birthDate" class="form-control"
                               value="${users.birthDate}" required
                        />
                        <div class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <fmt:message key="update.label.city" var="city"/>
                <label class="control-label col-xs-3" for="city"> ${city} :</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" name="city" id="city" placeholder="город"
                           value="${users.city}" required pattern="^[a-zA-Zа-яА-ЯёЁ ]*$">
                </div>
            </div>
            <span class="error"> ${loginwrong} ${loginchange}</span>
            <div class="form-group">
                <fmt:message key="update.label.password" var="password"/>
                <label class="control-label col-xs-3" for="inputPassword"> ${password} :</label>
                <div class="col-xs-9">
                    <input type="password" class="form-control" name="password" id="inputPassword"
                           placeholder="${password}" value="" required
                           pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*[\W|_]).{4,20}">
                </div>
            </div>
            <span class="error"> ${passwordwrong} ${change}</span>
            <div class="form-group">
                <fmt:message key="update.label.confirmpassword" var="confirmpassword"/>
                <label class="control-label col-xs-3" for="confirmPassword"> ${confirmpassword} :</label>
                <div class="col-xs-9">
                    <input type="password" name="confirmPassword" class="form-control" id="confirmPassword"
                           placeholder="${confirmpassword}" value=""
                           required oninput="validatePassword(this)">
                </div>
            </div>
            <span class="error"> ${wrongConfirm}</span>
            <div class="form-group">
                <fmt:message key="update.label.numberRecipe" var="numberRecipe"/>
                <label class="control-label col-xs-3" for="recipe">${numberRecipe} :</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" name="recipe" id="recipe" value="${users.numberReceipt}"
                           pattern="[\d]{4}" required>
                </div>
            </div>
            <span class="error"> ${numberwrong} </span>
            <div class="form-group">
                <fmt:message key="update.label.sex" var="sex"/>
                <div class="col-xs-9">
                    <label class="control-label col-xs-3"> ${sex} :</label>
                    <label class="radio-inline">
                        <fmt:message key="update.label.male" var="male"/>
                        <input type="radio" name="sex" id="r1" value="male" checked> ${male}
                    </label>
                    <label class="radio-inline registration-radio">
                        <fmt:message key="update.label.female" var="female"/>
                        <input type="radio" name="sex" id="r2" value="female"> ${female}
                    </label>
                </div>
            </div>
            <br/>
            <div class="form-group">
                <div class="col-xs-offset-3 col-xs-9">
                    <input type="submit" id="submit" class="btn btn-primary" value="update ">
                </div>
            </div>
            <span class="good">${goodUpdate}</span>
        </form>
    </div>
    <div class="col-sm-2"></div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="resources/js/bootstrap.js"></script>
</body>