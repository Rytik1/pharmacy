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
    <link href="resources/css/basket.css" rel="stylesheet">
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
        
        <table class="table table-bordered">
            <fmt:message key="basket.label.ResultOrder" var="ResultOrder"/>

            <caption>${ResultOrder}</caption>
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
               <tbody>
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


                </tr>
</tbody>

            </c:forEach>
            </c:if>
            <TFOOT>
            <tr>
                <fmt:message key="basket.label.resultSum" var="resultSum"/>
                <td colspan="7">${resultSum} <c:out value="${commonSUM}"/></td>
            </tr>
            </TFOOT>
        </table>


        <form method="post" action="controler" id="confirmOrder">
            <input type="hidden" name="command" value="CONFIRM_ORDER">
            <c:set var="listOrder" scope="request" value="${listOrder}"/>

            <fmt:message key="basket.button.buy" var="buy"/>
            <input type="submit" value="${buy}"/>

            ${notOrder}
            ${confirm}
        </form>
    </div>
    <div class="col-sm-2"></div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="resources/js/bootstrap.js"></script>
</body>
</html>
