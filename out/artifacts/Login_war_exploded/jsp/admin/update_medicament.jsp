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
    <title>Update Medicament</title>
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <link href="/resources/css/registration.css" rel="stylesheet">
</head>
<body>
<c:import url="../section/header.jsp"></c:import>
<br>
<br>
<div class="container text-center">
    <h3 class="good" float=center>${changeFoto}${update_medicament} ${name}</h3>
    <h3 class="error"
        float=center>${checkFoto}${notUniquePreparat} ${countwrong} ${countrywrong} ${pricewrong}</h3>
</div>
<div class="container text-center">
    <fmt:message key="administrator.label.updateMedicament" var="updateMedicament"/>
    <h1 class="glow">${updateMedicament}</h1>
</div>
<div class="container text-center">
    <div class="col-sm-2"></div>
    <div class="col-sm-8">
        <form name="update medicament" class="form-horizontal" method="post" action="controler" >
            <input type="hidden" name="id" value="${ medicament.id }"/>
            <input type="hidden" name="command" id="controllerCommand" value="UPDATE_MEDICAMENT">
            <div class="form-group">
                <fmt:message key="administrator.label.name" var="names"/>
                <label for="medicine-name" class="control-label col-xs-3">${names}: </label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" name="name" id="medicine-name" value="${medicament.name }"
                           pattern="[a-zA-Z1-9а-яА-Я ]{1,20}" required>
                </div>
            </div>
            <div class="form-group">
                <fmt:message key="update_medicament.label.dosage" var="dosage"/>

                <label for="medicine-medicineDosage" class="control-label col-xs-3">${dosage}:</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" name="dosage" id="medicine-medicineDosage"
                           value="${ medicament.dosage }" required>
                </div>
            </div>
            <div class="form-group">
                <fmt:message key="update_medicament.label.country" var="country"/>
                <label for="medicine-country" class="control-label col-xs-3">${country}:</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" name="country" id="medicine-country"
                           value="${ medicament.country }" required>
                </div>
            </div>
            <div class="form-group">
                <fmt:message key="update_medicament.label.recipe" var="recipe"/>
                <label class="control-label col-xs-3">${recipe}:</label>
                <div class="col-xs-9">
                   <c:if test="${medicament.recipeRequired eq 'true'}">
                    <label class="radio-inline">
                        <fmt:message key="update_medicament.label.yes" var="yes"/>
                        <input type="radio" class="medicine-recipe" name="recipe_required" value="true" id="yes"
                               checked> ${yes}  </input>
                    </label>
                    <label class="radio-inline">
                        <fmt:message key="update_medicament.label.no" var="no"/>
                        <input type="radio" class="medicine-recipe" name="recipe_required" value="false"
                               id="no"> ${no} </input>
                    </label>
                   </c:if>
                    <c:if test="${medicament.recipeRequired eq 'false'}">
                        <label class="radio-inline">
                            <fmt:message key="update_medicament.label.yes" var="yes"/>
                            <input type="radio" class="medicine-recipe" name="recipe_required" value="true" id="yes"
                                   > ${yes}  </input>
                        </label>
                        <label class="radio-inline">
                            <fmt:message key="update_medicament.label.no" var="no"/>
                            <input type="radio" class="medicine-recipe" name="recipe_required" checked value="false"
                                   id="no"> ${no} </input>
                        </label>
                    </c:if>
                </div>
            </div>
            <div class="form-group">
                <fmt:message key="update_medicament.label.price" var="price"/>
                <label for="medicine-price" class="control-label col-xs-3">${price}:</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" name="price" id="medicine-price" value="${ medicament.price }"
                           required pattern="^[0-9]{1,7}[.]?[0-9]{2}">
                    <fmt:message key="administrator.label.priceHelp" var="priceHelp"/>
                    <div class="help-block">${priceHelp}</div>
                </div>
            </div>
            <div class="form-group">
                <fmt:message key="update_medicament.label.count" var="count"/>
                <label for="medicine-amount" class="control-label col-xs-3">${count}:</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" name="amount_in_stock" id="medicine-amount"
                           value="${medicament.amountInStock}" required
                           pattern="^[0-9]{1,7}[.]?[0-9]{0,3}"/>
                    <fmt:message key="administrator.label.countHelp" var="countHelp"/>
                    <div class="help-block">${countHelp}
                    </div>
                </div>
            </div>
            <div class="form-group">
                <fmt:message key="update_medicament.button.update" var="update"/>
                <button type="submit" class="btn btn-danger"   id="confirm">${update}</button>
            </div>
        </form>
    </div>
    <div class="col-sm-2"></div>
</div>
<div align=center>
    <fmt:message key="update_medicament.label.changeFoto" var="changeFoto"/>
    <h4 class="glow">${changeFoto}:</h4>
    <form name="change foto" class="form-horizontal" enctype="multipart/form-data" method="post" action="controler"  >
        <input type="hidden" name="id" value="${ param.id }"/>
        <input type="hidden" name="command" value="CHANGE_FOTO">
        <fmt:message key="update_medicament.button.checkFoto" var="checkFoto"/>
        <input type="file" value="${checkFoto}" name="image" STYLE="color: white">
        <fmt:message key="update_medicament.button.updateFoto" var="updateFoto"/>
        <input type="submit" class="btn btn-danger" value="${updateFoto}">
    </form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="/resources/js/bootstrap.js"></script>
</body>
</html>