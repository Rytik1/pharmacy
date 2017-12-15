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
    <title>Basket</title>
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <link href="/resources/css/basket.css" rel="stylesheet">
</head>
<body>
 <c:import url="../section/header.jsp"></c:import>
<br>
<br>
 <div class="container text-center">
     <h3 class="good" float=center> ${confirm} </h3>
     <h3 class="error"
         float=center> ${notOrder}</h3>
 </div>
<div class="container text-center">
    <fmt:message key="basket.label.basket" var="basket"/>
    <c:if test="${not  empty listOrder}">
    <h1 class="glow">${basket}</h1>
</div>
<div class="container text-center">
    <div class="col-sm-2"></div>
    <div class="col-sm-8">
    <table class="scrolling-table table table-bordered">
            <fmt:message key="basket.label.ResultOrder" var="ResultOrder"/>
            <caption>${ResultOrder}</caption>
            <tbody>
            <c:if test="${not empty listOrder}">
            <tr>
                <th>№</th>
                <th>id</th>
                <fmt:message key="main.label.name" var="name"/>
                <th>${name} </th>
                <fmt:message key="basket.label.dosage" var="dosage"/>
                <th>${dosage} </th>
                <fmt:message key="basket.label.country" var="country"/>
                <th>${country} </th>
                <fmt:message key="basket.label.price" var="price"/>
                <th>${price} </th>
                <fmt:message key="basket.label.count" var="count"/>
                <th>${count} </th>
            </tr>
            <c:forEach var="elem" items="${listOrder}" varStatus="status">

                <tr>
                    <td><c:out value="${ status.count }"/></td>
                    <td><c:out value="${ elem.medicamentID }"/></td>
                    <td><c:out value="${ elem.name }"/></td>
                    <td><c:out value="${ elem.dosage }"/></td>
                    <td><c:out value="${ elem.country }"/></td>
                    <td><c:out value="${ elem.price }"/></td>
                    <td><c:out value="${ elem.count }"/></td>
                    <td>
                        <form method="post" action="controler" id="deleteOrder">
                            <input type="hidden" name="command" value="DELETE_ORDER">
                            <input type="hidden" name="id" value="${elem.id }"/>
                            <input type="hidden" name="count" value="${elem.count }"/>
                            <input type="hidden" name="medicamentId" value="${ elem.medicamentID }"/>
                            <fmt:message key="basket.button.delete" var="delete"/>
                            <input type="submit" value="${delete}"/>
                        </form>
                    </td>
                    <fmt:message key="basket.label.resultSum" var="resultSum"/>
                </tr>
            </c:forEach>
            </tbody>
            <fmt:message key="basket.label.resultSum" var="resultSum"/>
            <h3> ${resultSum} <c:out value="${commonSUM}"/></h3>
            <c:if test="${not empty param.sales}">
                <fmt:message key="basket.label.resultSumSales" var="resultSumSales"/>
                <h3>
                        ${resultSumSales} <fmt:formatNumber value="${commonSUM*param.sales}"
                                                            maxFractionDigits="2"/></h3>
            </c:if>
            <c:if test="${not empty param.saleFromCheckNumber}">
                <fmt:message key="basket.label.resultSumSalesCheckNumb" var="resultSumSalesCheckNumb"/>
                <h3>
                        ${resultSumSalesCheckNumb} <fmt:formatNumber value="${commonSUM*param.saleFromCheckNumber}"
                                                            maxFractionDigits="2"/></h3>
            </c:if>
            </c:if>
        </table>
        <form method="post" action="controler" id="confirmOrder">
            <input type="hidden" name="command" value="CONFIRM_ORDER">
            <c:set var="listOrder" scope="request" value="${listOrder}"/>
            <fmt:message key="basket.button.buy" var="buy"/>
            <input class="button4" type="submit" value="${buy}"/>
        </form>
    </div>
    <div class="col-sm-2"></div>
    </c:if>
</div>
<div class="container text-center">
    <c:if test="${  empty listOrder}">
        <fmt:message key="basket.label.basketEmpty" var="basketEmpty"/>
    <h2 class="glow">${basketEmpty}</h2>
    </c:if>
<c:import url="../section/footer.jsp"></c:import>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="/resources/js/bootstrap.js"></script>
</body>

</html>
