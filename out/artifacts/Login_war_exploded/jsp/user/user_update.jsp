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
    <title>Update user</title>
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <link href="/resources/css/registration.css" rel="stylesheet">
</head>
<body>
<c:import url="../section/header.jsp"></c:import>
<br>
<br>
<br>
<div class="container text-center">
    <fmt:message key="update.label.update" var="update"/>
    <h1 class="glow">${update}</h1>
</div>
<div class="container text-center">
    <h3 class="good" float=center> ${goodUpdate}${goodPassUpdate} </h3>
</div>
<div class="container text-center">
    <h3 class="error" float=center> ${wrongChangePass}  </h3>
</div>
<div class="container text-center">
    <div class="col-sm-2"></div>
    <div class="col-sm-8">
        <form name="updateUser" class="form-horizontal" action="controler" method="post">
            <input type="hidden" name="command" value="UPDATE_USER"/>
            <input type="hidden" name="login" value="${users.login}"/>
            <input type="hidden" name="email" value="${users.email}"/>
            <div class="form-group">
                <fmt:message key="update.label.login" var="login"/>
                <label class="control-label col-xs-3" for="login"> ${login} :</label>
                <div class="col-xs-9">
                    <input type="text" value="${users.login}" readonly id="login" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <fmt:message key="update.label.email" var="email"/>
                <label class="control-label col-xs-3" for="email"> ${email}:</label>
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
                    <fmt:message key="update.label.birthdate" var="birthdate"/>${birthdate}:</label>
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
            <h4 class="error"> ${loginwrong} ${loginchange}</h4>

            <div class="form-group">
                <fmt:message key="update.label.numberRecipe" var="numberRecipe"/>
                <label class="control-label col-xs-3" for="recipe">${numberRecipe} :</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" name="recipe" id="recipe" value="${users.numberReceipt}"
                           pattern="[\d]{4}" required>
                    <fmt:message key="signup.label.helpRecipe" var="helpRecipe"/>
                    <div class="help-block">${helpRecipe}</div>
                </div>
            </div>
            <h4 class="error"> ${numberwrong} ${recipechange} </h4>
            <div class="form-group">
                <fmt:message key="update.label.sex" var="sex"/>
                <div class="col-xs-9">
                    <label class="control-label col-xs-3"> ${sex} :</label>
                    <c:if test="${gender eq 'FAMALE'}">
                        <label class="radio-inline">
                            <fmt:message key="update.label.male" var="male"/>
                            <input type="radio" name="sex" id="r1" value="male"> ${male}
                        </label>
                        <label class="radio-inline registration-radio">
                            <fmt:message key="update.label.female" var="female"/>
                            <input type="radio" name="sex" id="r2" value="famale" checked> ${female}
                        </label>
                    </c:if>
                    <c:if test="${gender eq 'MALE'}">
                        <label class="radio-inline">
                            <fmt:message key="update.label.male" var="male"/>
                            <input type="radio" name="sex" id="r1" value="male" checked> ${male}
                        </label>
                        <label class="radio-inline registration-radio">
                            <fmt:message key="update.label.female" var="female"/>
                            <input type="radio" name="sex" id="r2" value="famale"> ${female}
                        </label>
                    </c:if>
                </div>
            </div>
            <br/>
            <div class="form-group">
                <div class="col-xs-offset-3 col-xs-9">
                    <fmt:message key="update.button.updates" var="updates"/>
                    <input type="submit" id="submit" class="button9" value="${updates} ">
                </div>
            </div>
        </form>
        <br>
        <fmt:message key="update.label.updatePass" var="updatePass"/>
        <h1 class="glow">${updatePass}</h1>
    <form name="change pass" class="form-horizontal" action="controler" method="post">
        <input type="hidden" name="command" value="CHANGE_USER_PASSWORD"/>
        <input type="hidden" name="login" value="${users.login}"/>
        <div class="form-group">
            <fmt:message key="update.label.oldpassword" var="oldpassword"/>
            <label class="control-label col-xs-3" for="inputPassword"> ${oldpassword} :</label>
            <div class="col-xs-9">
                <input type="password" class="form-control" name="oldpassword" id="inputPassword"
                       placeholder="${oldpassword}" value="" required
                       pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*[\W|_]).{4,20}">
                <fmt:message key="signup.label.passwordHelp" var="passwordHelp"/>
                <div class="help-block">${passwordHelp}</div>
            </div>
        </div>
        <h4 class="error"> ${oldpasswordwrong} </h4>
        <div class="form-group">
            <fmt:message key="update.label.newPassword" var="newPassword"/>
            <label class="control-label col-xs-3" for="inputPassword"> ${newPassword} :</label>
            <div class="col-xs-9">
                <input type="password" class="form-control" name="password" id="inputPassword"
                       placeholder="${newPassword}" value="" required
                       pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*[\W|_]).{4,20}">
                <fmt:message key="signup.label.passwordHelp" var="passwordHelp"/>
                <div class="help-block">${passwordHelp}</div>
            </div>
        </div>
        <h4 class="error"> ${passwordwrong} ${change}</h4>
        <div class="form-group">
            <fmt:message key="update.label.confirmpassword" var="confirmpassword"/>
            <label class="control-label col-xs-3" for="confirmPassword"> ${confirmpassword}:</label>
            <div class="col-xs-9">
                <input type="password" name="confirmPassword" class="form-control" id="confirmPassword"
                       placeholder="${confirmpassword}" value=""
                       required oninput="validatePassword(this)">
            </div>
        </div>
        <h4 class="error"> ${wrongConfirm}</h4>
        <br/>
        <div class="form-group">
            <div class="col-xs-offset-3 col-xs-9">
                <fmt:message key="update.button.updates" var="updates"/>
                <input type="submit" id="submit" class="button9" value="${updates} ">
            </div>
        </div>
    </form>
    </div>
    <div class="col-sm-2"></div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="/resources/js/bootstrap.js"></script>
</body>