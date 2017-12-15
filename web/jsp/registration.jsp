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
    <fmt:message key="signup.label.registration" var="registration"/>
    <title>${registration}</title>
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <link href="/resources/css/registration.css" rel="stylesheet">
</head>
<body>
<br>
<br>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <img src="/resources/image/logo.jpg" width="70%">
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li>
                <form class="navbar-form navbar-right">
                    <form name="logoutForm" method="POST" action="controler">
                        <input type="hidden" name="command" value="logout"/>
                        <fmt:message key="signout.button.submit" var="Logout"/>
                        <input class="button9" type="submit" value="${Logout}"/>
                    </form>
                </form>
            </li>
        </ul>
    </div>
</nav>
<br>
<div class="container text-center">
    <h1 class="glow">${registration}</h1>
</div>
<div class="container text-center">
    <div class="col-sm-2"></div>
    <div class="col-sm-8">
        <form name="registration" class="form-horizontal" action="controler" method="post">
            <input type="hidden" name="command" value="registration"/>
            <div class="form-group">
                <fmt:message key="signup.label.surname" var="surnames"/>
                <label class="control-label col-xs-3" for="surname"> ${surnames} :</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" id="surname" name="surname" id="surname"
                           placeholder="${surnames} " value="" required pattern="^[a-zA-Zа-яА-ЯёЁ]*$">
                </div>
            </div>
            <div class="form-group">
                <fmt:message key="signup.label.name" var="name"/>
                <label class="control-label col-xs-3" for="name">${name}:</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" name="name" id="name" placeholder="${name}" value=""
                           required pattern="^[a-zA-Zа-яА-ЯёЁ]*$"/>
                </div>
            </div>
            <div class="form-group">
                <fmt:message key="signup.label.birthdate" var="birthdate"/>

                <label for="birth" class="control-label col-xs-3" for="birth">${birthdate}:</label>
                <div class="col-xs-9">
                    <input type="date" id="birth" name="birthDate" class="form-control" value="" required
                    />
                </div>
            </div>
            <div class="form-group">
                <fmt:message key="signup.label.city" var="city"/>
                <label class="control-label col-xs-3" for="city"> ${city} :</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" name="city" id="city" placeholder="${city}" value=""
                           required pattern="^[a-zA-Zа-яА-ЯёЁ ]*$"/>
                </div>
            </div>
            <div class="form-group">
                <fmt:message key="signup.label.email" var="email"/>
                <label class="control-label col-xs-3" for="inputEmail"> ${email}:</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" name="email" id="inputEmail" placeholder="${email}" required
                           pattern="[_A-Za-z0-9-\+]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})">
                </div>
            </div>
            <h4 class="error">${emailwrong} ${emailchange}</h4>
            <div class="form-group">
                <fmt:message key="signup.label.login" var="login"/>
                <label class="control-label col-xs-3" for="login"> ${login}:</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" name="login" id="login" placeholder="${login}" required
                           pattern="[\w-]{3,20}">
                </div>
            </div>
            <h4 class="error"> ${loginwrong} ${loginchange}</h4>
            <div class="form-group">
                <fmt:message key="signup.label.password" var="password"/>
                <label class="control-label col-xs-3" for="inputPassword"> ${password} :</label>
                <div class="col-xs-9">
                    <input type="password" class="form-control" name="password" id="inputPassword"
                           placeholder="${password}" value=""
                           pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*[\W|_]).{4,20}" required>
                    <fmt:message key="signup.label.passwordHelp" var="passwordHelp"/>
                    <div class="help-block">${passwordHelp}</div>
                </div>
            </div>
            <h4 class="error"> ${passwordwrong}</h4>
            <div class="form-group">
                <fmt:message key="signup.label.confirmpassword" var="confirmpassword"/>
                <label class="control-label col-xs-3" for="confirmPassword"> ${confirmpassword} :</label>
                <fmt:message key="signup.label.password" var="password"/>
                <div class="col-xs-9">
                    <input type="password" class="form-control" name="confirmPassword" id="confirmPassword"
                           placeholder="${confirmpassword}" required data-match="#inputPassword"
                           data-match-error="Whoops, these don't match">
                </div>
            </div>
            <h4 class="error"> ${wrongConfirm}</h4>
            <div class="form-group">
                <fmt:message key="signup.label.numberRecipe" var="numberRecipe"/>
                <label class="control-label col-xs-3" for="recipe">${numberRecipe} :</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" name="recipe" id="recipe" placeholder="${numberRecipe}"
                           value="" pattern="[\d]{4}">
                    <fmt:message key="signup.label.helpRecipe" var="helpRecipe"/>
                    <div class="help-block">${helpRecipe}</div>
                </div>
            </div>
            <h4 class="error">${numberwrong}${recipechange}</h4>
            <div class="form-group">
                <div class="col-xs-9">
                    <fmt:message key="signup.label.sex" var="sex"/>
                    <label class="control-label col-xs-3"> ${sex} :</label>
                    <label class="radio-inline">
                        <fmt:message key="signup.label.male" var="male"/>
                        <input type="radio" name="sex" id="r1" value="male" checked> ${male}
                    </label>
                    <label class="radio-inline registration-radio">
                        <fmt:message key="signup.label.female" var="female"/>
                        <input type="radio" name="sex" id="r2" value="famale"> ${female}
                    </label>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-offset-3 col-xs-9">
                    <fmt:message key="signup.button.registration" var="registration"/>

                    <input type="submit" id="submit" class="button9" value=" ${registration} ">
                    <fmt:message key="signup.button.clear" var="clear"/>
                    <input type="reset" id="clear" class="button9" value=" ${clear} ">
                </div>
            </div>
        </form>
    </div>
    <div class="col-sm-2"></div>
</div>
<br>
<c:import url="section/footer.jsp"></c:import>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="resources/js/bootstrap.js"></script>
</body>
</html>