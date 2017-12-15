<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="localization.bundler"/>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <link href="/resources/css/main.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="/resources/js/general.js"></script>
    <script src="/resources/js/time.js"></script>

    <title>Main</title>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <img src="/resources/image/logo.jpg" width="70%">
        </div>
        <ul class="nav navbar-nav">
            <fmt:message key="main.label.home" var="home"/>
            <li class="dropdown">
                <fmt:message key="main.label.action" var="action"/>
                <a href="#" class="button9" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-
                   haspopup="true" id="action" aria-expanded="false"> ${action} <span class="caret"></span></a>
                <ul class="dropdown-menu" class="hides" id="hide">
                    <li>
                        <form name="updateUser" method="post" action="/controler" id="update">
                            <input type="hidden" name="command" value="GO_TO_UPDATE_USER">
                            <input type="hidden" name="login" value="${user}">
                            <fmt:message key="main.button.update" var="update"/>
                            <input class="button9" type="submit" id="submit" value="${update}"/>
                        </form>
                    </li>
                    <li>
                        <form name="resipeList" method="POST" action="/controler">
                            <fmt:message key="main.button.resipeList" var="resipeList"/>
                            <input type="hidden" name="login" value="${user}">
                            <input type="hidden" name="command" value="SHOW_RECIPE"/>
                            <input class="button9" type="submit" value="${resipeList}"/>
                        </form>
                    </li>
                    <li>
                        <form name="ALL_MEDICAMENT" method="POST" action="/controler">
                            <input type="hidden" name="command" value="ALL_MEDICAMENT"/>
                            <input type="hidden" name="page" value="/jsp/user/main.jsp"/>
                            <fmt:message key="main.label.allMedicament" var="allMedicament"/>
                            <input class="button9"   type="submit" value="${allMedicament} "/>
                        </form>
                    </li>
                    <li>
                        <form name="HISTORY" method="POST" action="/controler">
                            <input type="hidden" name="command" value="HISTORY"/>
                            <input type="hidden" name="page" value="/jsp/user/main.jsp"/>
                            <fmt:message key="main.button.buyingHistory" var="buyingHistory"/>
                            <input class="button9" type="submit" value="${buyingHistory} "/>
                        </form>
                    </li>
                    <li>
                        <fmt:message key="main.label.game" var="game"/>
                        <h4> ${game}</h4>
                    </li>
                    <li>
                        <form name="gameSale" method="POST" action="/controler">
                            <input type="hidden" name="command" value="gameSale"/>

                            <fmt:message key="main.button.gameSale" var="gameSale"/>
                            <input class="button9" type="submit" value="${gameSale} "/>
                        </form>
                    </li>
                    <li>
                        <form name="game" method="POST" action="/controler">
                            <input type="hidden" name="command" value="game"/>

                            <fmt:message key="main.button.game" var="game"/>
                            <input class="button9" type="submit" value="${game} "/>
                        </form>
                    </li>
                    <li>
                        <form name="game check number" method="POST" action="/controler">
                            <input type="hidden" name="command" value="GAME_CHECK_NUMBER"/>
                            <fmt:message key="main.button.gameCheckNumber" var="gameCheckNumber"/>
                            <input class="button9" type="submit" value="${gameCheckNumber} "/>
                        </form>
                    </li>
                </ul>
            </li>
            <form class="navbar-form navbar-left" name="findMedicament" method="get" action="/controler">
                <input type="hidden" name="command" value="FIND_MEDICAMENT"/>
                <input type="hidden" name="page" value="/jsp/user/main.jsp"/>
                <div class="form-group">
                    <fmt:message key="main.label.enterMedicament" var="findMedicament"/>
                    <input type="text" class="form-control" placeholder="${findMedicament}" name="name"
                           id="lookfor"/>
                </div>
                <fmt:message key="main.button.find" var="find"/>
                <input class="button9" type="submit" value="${find}"/>
            </form>

        <button id="but1"><img style=" width: 40;" src="/resources/image/time.png " alt="time On/Of"></button>
            <p class="time" id="time"></p>

         </ul>

        <ul class="nav navbar-nav navbar-right">
            <li>
                <form name="basket" method="POST" action="/controler">
                    <fmt:message key="main.label.basket" var="basket"/>
                    <input type="hidden" name="command" value="GO_TO_BASKET"/>
                    <fmt:message key="main.label.USER" var="USER"/>
                    <label for="basket">${USER} ${user}</label>
                    <input class="button9" id=basket type="submit" value="${basket}"/>
                </form>
            </li>
            <li>
                <form name="logout" method="POST" action="/controler">
                    <input type="hidden" id="logout" name="command" value="logout"/>
                    <fmt:message key="signout.button.submit" var="Logout"/>
                    <input class="button9" type="submit" value="${Logout}"/>
                </form>
            </li>
        </ul>
    </div>
</nav>
<br>
<br>
<br>
<div class="container text-center">
    <h3 class="good" float=center> ${sendEmail} ${confirmAdd}${orderInBasket} ${newRecipe}</h3>
    <h3 class="error"
        float=center> ${invalideСount}${historyListEmpty}${notRecipe} ${notCountMed} ${notmedicament} ${medicamentNameFalse}</h3>
</div>
<div class="container text-center">
    <div class="col-sm-2"></div>
    <div class="col-sm-8">
        <c:if test="${not empty findmedicamentList}">
            <table class="scrolling-table table table-bordered">
                <fmt:message key="main.label.findResult" var="findResult"/>
                <caption>${findResult}</caption>
                <tbody>
                <tr>
                    <th>№</th>
                    <th>id</th>
                    <fmt:message key="main.label.name" var="name"/>
                    <th>${name} </th>
                    <fmt:message key="main.label.dosage" var="dosage"/>
                    <th>${dosage} </th>
                    <fmt:message key="main.label.country" var="country"/>
                    <th>${country} </th>
                    <fmt:message key="main.label.price" var="price"/>
                    <th>${price} </th>
                    <fmt:message key="main.label.amountInStock" var="amountInStock"/>
                    <th>${amountInStock} </th>
                    <fmt:message key="administrator.label.foto" var="foto"/>
                    <th>${foto} </th>
                    <fmt:message key="main.label.isRecipe" var="isRecipe"/>
                    <th>${isRecipe} </th>
                </tr>
                <c:forEach var="elem" items="${findmedicamentList}" varStatus="status">
                    <tr>
                        <td><c:out value="${ status.count }"/></td>
                        <td><c:out value="${ elem.id }"/></td>
                        <td><c:out value="${ elem.name }"/></td>
                        <td><c:out value="${ elem.dosage }"/></td>
                        <td><c:out value="${ elem.country }"/></td>
                        <td><c:out value="${ elem.price }"/></td>
                        <td><c:out value="${ elem.amountInStock }"/></td>
                        <td><img src="${elem.imagePath}" width="100"></td>

                        <td>
                            <c:set var="isResipe" value="${elem.recipeRequired }"/>
                            <c:choose>
                                <c:when test="${ isResipe eq 'true' }">
                                    <fmt:message key="main.label.yes" var="yes"/>
                                    <c:out value="${yes}"/>
                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="main.label.no" var="no"/>
                                    <c:out value="${no}"/>
                                </c:otherwise>
                            </c:choose></td>
                        <td>
                            <form name="do order" method="post" action="/controler" id="order">
                                <input type="hidden" name="id" value="${ elem.id }"/>
                                <input type="hidden" name="name" value="${ elem.name }"/>
                                <input type="hidden" name="dosage" value="${ elem.dosage }"/>
                                <input type="hidden" name="country" value="${ elem.country }"/>
                                <input type="hidden" name="count" value="${ elem.amountInStock }"/>
                                <input type="hidden" name="price" value="${ elem.price }"/>
                                <input type="hidden" name="recipeRequired" value="${ elem.recipeRequired }"/>
                                <c:if test="${ elem.amountInStock >0}">
                                    <input type="hidden" name="command" id="controlerCommand" value="DO_ORDER">

                                    <fmt:message key="main.button.inBasket" var="basket"/>
                                    <input type="submit" value="${basket}   "/>
                                    <fmt:message key="main.label.count" var="count"/>
                                    <input type="text" placeholder="${count}" required class="form-control"
                                           name="countOrder"/>
                                </c:if>
                                <c:if test="${ elem.amountInStock eq 0}">
                                    <input type="hidden" name="command" id="controlerCommand"
                                           value="REQUEST_INFORMATION_COUNT_MEDICAMENT">
                                    <fmt:message key="main.button.request" var="request"/>
                                    <input type="submit" value="${request}   "/>
                                </c:if>
                           </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${not empty recipeList}">
            <table class="scrolling-table table table-bordered">
                <fmt:message key="main.label.findResult" var="findResult"/>
                <caption>${findResult}</caption>
                <tbody>
                <tr>
                    <th>№</th>
                    <th>id</th>
                    <fmt:message key="main.label.name" var="name"/>
                    <th>${name} </th>
                    <fmt:message key="main.label.dosage" var="dosage"/>
                    <th>${dosage} </th>
                    <fmt:message key="main.label.validityRecipe" var="validityRecipe"/>
                    <th>${validityRecipe} </th>
                    <fmt:message key="main.label.state" var="state"/>
                    <th>${state} </th>
                </tr>
                <c:forEach var="elem" items="${recipeList}" varStatus="status">
                    <tr>
                        <td><c:out value="${ status.count }"/></td>
                        <td><c:out value="${ elem.medicamentId }"/></td>
                        <td><c:out value="${ elem.name }"/></td>
                        <td><c:out value="${ elem.dosage }"/></td>
                        <td><c:out value="${ elem.validityRecipe }"/></td>
                        <td>
                            <c:set var="isResipe" value="${elem.state }"/>
                            <c:choose>
                                <c:when test="${ isResipe eq 'false' }">
                                    <fmt:message key="main.label.end" var="end"/>
                                    <h4 class="error">${end}</h4>
                                    <fmt:message key="main.button.continue" var="continues"/>
                                    <form method="post" action="/controler">
                                        <input type="hidden" value="SHOW_RECIPE" name="command">
                                        <input type="hidden" value="request" name="request">
                                        <input type="hidden" name="page" value="/jsp/user/main.jsp"/>
                                        <input type="submit" class="button9" value="${continues}"/>
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="main.label.good" var="good"/>
                                    <c:out value="${good}"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${not empty historyList}">
            <table class="scrolling-table table table-bordered">
                <fmt:message key="administrator.label.findResult" var="findResult"/>
                <caption>${findResult}</caption>
                <tbody>
                <tr>
                    <th>№</th>
                    <fmt:message key="administrator.label.name" var="name"/>
                    <th>${name} </th>
                    <fmt:message key="administrator.label.count" var="count"/>
                    <th>${count} </th>
                    <fmt:message key="administrator.label.date" var="date"/>
                    <th>${date} </th>
                </tr>
                <c:forEach var="elem" items="${historyList}" varStatus="status">
                    <tr>
                        <td><c:out value="${ status.count }"/></td>
                        <td><c:out value="${ elem.name }"/></td>
                        <td><c:out value="${ elem.countByuing }"/></td>
                        <td><c:out value="${ elem.dateBuying }"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
    <div class="col-sm-2"></div>
</div>
<div class="container text-center"><a href="/controler?command=game"> <img src="../../resources/image/4.jpg" width="200"
                                                                           height="200" alt="игра" style="    position: fixed;
    left: 0;
    bottom: 0;
    padding: 10px;"></a>
    <a href="/controler?command=GAME_CHECK_NUMBER"> <img src="../../resources/image/sale10.png" width="200" height="200"
                                                         alt="игра" style="    position: fixed;
    right: 11;
    top: 58;
    padding: 10px;"></a>
    <a href="/controler?command=gameSale"> <img src="../../resources/image/sales.jpg" width="200" height="200"
                                                alt="игра" style="    position: fixed;
    right: 0;
    bottom: 0;
    padding: 10px;"></a>
</div>
<c:import url="../section/footer.jsp"></c:import>
<c:remove var="notCountMed" scope="session"></c:remove>
<c:remove var="findmedicamentList" scope="session"></c:remove>
<c:remove var="medicamentNameFalse" scope="session"></c:remove>
<c:remove var="orderInBasket" scope="session"></c:remove>
<c:remove var="notRecipe" scope="session"></c:remove>
<c:remove var="confirmAdd" scope="session"></c:remove>
<c:remove var="notCountMed" scope="session"></c:remove>
<c:remove var="invalideСount" scope="session"></c:remove>
<c:remove var="notmedicament" scope="session"></c:remove>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="/resources/js/bootstrap.js"></script>
</body>
</html>
