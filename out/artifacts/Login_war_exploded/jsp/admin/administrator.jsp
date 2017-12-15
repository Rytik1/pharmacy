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
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <link href="/resources/css/administrator.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="/resources/js/general.js"></script>
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
                   haspopup="true" aria-expanded="false" id="action"> ${action} <span class="caret"></span></a>
                <ul class="dropdown-menu" class="hides" id="hide">
                    <li>
                        <form name="add med" method="post" action="controler" id="update">
                            <input type="hidden" name="command" value="GO_TO_ADD_MEDICAMENT">
                            <input type="hidden" name="login" value="${user}">
                            <fmt:message key="administrator.button.addMed" var="addMed"/>
                            <input class="button9" type="submit" id="submit" value="${addMed}"/>
                        </form>
                    </li>
                    <li>
                        <form name="all med" method="POST" action="controler">
                            <input type="hidden" name="command" value="ALL_MEDICAMENT"/>
                            <input type="hidden" name="page" value="/jsp/admin/administrator.jsp"/>
                            <fmt:message key="main.label.allMedicament" var="allMedicament"/>
                            <input class="button9" type="submit" value="${allMedicament} "/>
                        </form>
                    </li>
                    <li>
                        <form name="history" method="POST" action="controler">
                            <input type="hidden" name="command" value="HISTORY"/>
                            <input type="hidden" name="page" value="/jsp/admin/administrator.jsp"/>
                            <fmt:message key="main.button.buyingHistory" var="buyingHistory"/>
                            <input class="button9" type="submit" value="${buyingHistory} "/>
                        </form>
                    </li>
                    <li>
                        <form name="show all user" method="POST" action="controler">
                            <input type="hidden" name="command" value="SHOW_ALL_USER"/>
                            <fmt:message key="administrator.button.showUser" var="showUser"/>
                            <input class="button9" type="submit" value="${showUser}"/>
                        </form>
                    </li>
                    <li>
                        <form name="statistic" method="POST" action="controler">
                            <input type="hidden" name="command" value="STATISTIC"/>
                            <fmt:message key="administrator.button.statistic" var="statistic"/>
                            <input class="button9" type="submit" value="${statistic}"/>
                        </form>
                    </li>
                </ul>
            </li>
            <form class="navbar-form navbar-left">
                <form name="findMedicament" method="POST" action="controler">
                    <input type="hidden" name="command" value="FIND_MEDICAMENT"/>
                    <input type="hidden" name="page" value="/jsp/admin/administrator.jsp"/>
                    <div class="form-group">
                        <fmt:message key="main.label.enterMedicament" var="findMedicament"/>
                        <input type="text" class="control-label" placeholder="${findMedicament}" name="name"
                               id="lookfor"/>
                    </div>
                    <fmt:message key="main.button.find" var="find"/>
                    <input class="button9" type="submit" value="${find}"/>
                </form>
            </form>
            <form class="navbar-form navbar-left">
                <form name="find user" method="POST" action="controler">
                    <input type="hidden" name="command" value="FIND_USER_BY_LOGIN"/>
                    <div class="form-group">
                        <fmt:message key="administrator.label.searchByLogin" var="searchByLogin"/>
                        <input type="text" class="control-label" placeholder="${searchByLogin}" name="login" id="look"/>
                    </div>
                    <fmt:message key="administrator.button.findUser" var="findUsers"/>
                    <input class="button9" type="submit" value="${find}"/>
                </form>
            </form>
            <button id="but1"><img style=" width: 40;" src="/resources/image/stat.png " alt="statistic On/Of"></button>

        </ul>
        <ul class="nav navbar-nav navbar-right">

            <li>
                <form name="logout" method="POST" action="controler">
                    <input type="hidden" id="logout" name="command" value="logout"/>
                    <fmt:message key="main.label.USER" var="USER"/>
                    <label class="nameUser" for="submit">${USER} ${user}</label>
                    <fmt:message key="signout.button.submit" var="Logout"/>
                    <input class="button9" id="submit" type="submit" value="${Logout}"/>
                </form>
            </li>
        </ul>
    </div>
</nav>
<br>
<br>
<div class="container text-center">
    <fmt:message key="administrator.label.countbuy" var="countbuy"/>
    <fmt:message key="administrator.label.topMed" var="topMed"/>
    <fmt:message key="administrator.label.countUser" var="countUser"/>
    <fmt:message key="administrator.label.updateStat" var="updateStat"/>
    <c:if test="${state eq 'true'}">
        <marquee id="scrol" scrollamount="7" width="590">
            <h3>${countbuy} ${countBuying}; ${topMed} ${bestMedicament}; ${countUser} ${countRegistrUser};</h3>
        </marquee>
    </c:if>
    <c:if test="${state ne 'true'}">
        <marquee id="scrol" scrollamount="7" width="590"><h3>${updateStat} </h3></marquee>
    </c:if>
</div>
<div class="container text-center">
    <h3 class="error"
        float=center> ${notLogin}${loginerrorenter}${notmedicament}${resultdelete}${medicamentNameFalse} ${resultdelete} </h3>
</div>
<div class="container text-center">
    <div class="col-sm-2"></div>
    <div class="col-sm-8">
        <c:if test="${not empty findmedicamentList}">
            <table class="scrolling-table table table-bordered">
                <fmt:message key="administrator.label.findResult" var="findResult"/>
                <caption>${findResult}</caption>
                <tbody>
                <tr>
                    <th>№</th>
                    <th>id</th>
                    <fmt:message key="administrator.label.name" var="name"/>
                    <th>${name} </th>
                    <fmt:message key="administrator.label.dosage" var="dosage"/>
                    <th>${dosage} </th>
                    <fmt:message key="administrator.label.country" var="country"/>
                    <th>${country} </th>
                    <fmt:message key="administrator.label.price" var="price"/>
                    <th>${price} </th>
                    <fmt:message key="administrator.label.count" var="amountInStock"/>
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
                            </c:choose>
                        </td>
                        <td>
                            <form name="update medicament" method="post" action="controler" id="medicineUpdate">
                                <input type="hidden" name="command" id="controlerCommand" value="GO_UPDATE">
                                <input type="hidden" name="id" value="${ elem.id }"/>
                                <input type="hidden" name="name" value="${ elem.name }"/>
                                <input type="hidden" name="dosage" value="${ elem.dosage }"/>
                                <input type="hidden" name="country" value="${ elem.country }"/>
                                <input type="hidden" name="count" value="${ elem.amountInStock }"/>
                                <input type="hidden" name="price" value="${ elem.price }"/>
                                <input type="hidden" name="recipeRequired" value="${ elem.recipeRequired }"/>
                                <fmt:message key="administrator.button.update" var="update"/>
                                <input type="submit" value="${update}  "/>
                            </form>
                            <form name="delet med" method="post" action="controler" id="medicineDELETE">
                                <input type="hidden" name="command" value="GO_TO_DELETE_MEDICAMENT">
                                <input type="hidden" name="id" value="${ elem.id }"/>
                                <input type="hidden" name="name" value="${ elem.name }"/>
                                <input type="hidden" name="dosage" value="${ elem.dosage }"/>
                                <input type="hidden" name="country" value="${ elem.country }"/>
                                <input type="hidden" name="count" value="${ elem.amountInStock }"/>
                                <input type="hidden" name="price" value="${ elem.price }"/>
                                <input type="hidden" name="recipeRequired" value="${ elem.recipeRequired }"/>
                                <fmt:message key="administrator.button.delete" var="delete"/>
                                <input type="submit" value="${delete}   "/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${not empty user_list}">
            <table class="scrolling-table table table-bordered">
                <fmt:message key="administrator.label.findResult" var="findResult"/>
                <caption>${findResult}</caption>
                <tbody>
                <tr>
                    <th>№</th>
                    <th>id</th>
                    <fmt:message key="administrator.login" var="login"/>
                    <th>${login} </th>
                    <fmt:message key="administrator.label.gender" var="gender"/>
                    <th>${gender} </th>
                    <fmt:message key="administrator.label.email" var="email"/>
                    <th>${email} </th>
                    <fmt:message key="administrator.label.names" var="names"/>
                    <th>${names} </th>
                    <fmt:message key="administrator.label.surname" var="surname"/>
                    <th>${surname} </th>
                    <fmt:message key="administrator.label.birthDate" var="birthDate"/>
                    <th>${birthDate} </th>
                    <fmt:message key="administrator.label.numberReceipt" var="numberReceipt"/>
                    <th>${numberReceipt} </th>
                    <fmt:message key="administrator.label.role" var="role"/>
                    <th>${role} </th>
                    <fmt:message key="administrator.label.sity" var="sity"/>
                    <th>${sity} </th>
                </tr>
                <c:forEach var="elem" items="${user_list }" varStatus="status">
                    <tr>
                        <td><c:out value="${ status.count }"/></td>
                        <td><c:out value="${ elem.id }"/></td>
                        <td><c:out value="${ elem.login }"/></td>
                        <td><c:out value="${ elem.gender }"/></td>
                        <td><c:out value="${ elem.email }"/></td>
                        <td><c:out value="${ elem.name }"/></td>
                        <td><c:out value="${ elem.surname }"/></td>
                        <td><c:out value="${ elem.birthDate }"/></td>
                        <td><c:out value="${ elem.numberReceipt }"/></td>
                        <td><c:out value="${ elem.role }"/></td>
                        <td><c:out value="${ elem.city }"/></td>
                        <td>
                            <form name="delete user" method="post" action="controler">
                                <input type="hidden" name="command" value="GO_TO_DELETE_USER">
                                <input type="hidden" name="id" value="${ elem.id }"/>
                                <input type="hidden" name="login" value="${ elem.login }"/>
                                <input type="hidden" name="gender" value="${elem.gender  }"/>
                                <input type="hidden" name="email" value="${ elem.email }"/>
                                <input type="hidden" name="name" value="${ elem.name }"/>
                                <input type="hidden" name="surname" value="${ elem.surname }"/>
                                <input type="hidden" name="birthDate" value="${ elem.birthDate }"/>
                                <input type="hidden" name="numberReceipt" value="${ elem.numberReceipt }"/>
                                <input type="hidden" name="role" value="${ elem.role }"/>
                                <input type="hidden" name="city" value="${ elem.city }"/>
                                <fmt:message key="administrator.button.deleteUser" var="deleteUser"/>
                                <input type="submit" value="${deleteUser}  "/>
                            </form>
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
        <fmt:message key="administrator.button.submit" var="Logout"/>
    </div>

    <div style="border-color:black">
        <div class="col-sm-2"></div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="/resources/js/bootstrap.js"></script>
        <c:remove var="notCountMed" scope="session"></c:remove>
        <c:remove var="findmedicamentList" scope="session"></c:remove>
        <c:remove var="medicamentNameFalse" scope="session"></c:remove>
        <c:remove var="orderInBasket" scope="session"></c:remove>
        <c:remove var="notRecipe" scope="session"></c:remove>
        <c:remove var="confirmAdd" scope="session"></c:remove>
        <c:remove var="notCountMed" scope="session"></c:remove>
        <c:remove var="invalideСount" scope="session"></c:remove>
        <c:remove var="notmedicament" scope="session"></c:remove>
</body>
</html>
