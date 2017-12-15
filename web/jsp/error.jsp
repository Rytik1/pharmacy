<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="localization.bundler"/>
<html>
<head>
    <title>Error</title>
 </head>
<body>
<div class="error-info">
         <fmt:message key="error_page.content.request_from"/>
        ${pageContext.errorData.requestURI}
        <fmt:message key="error_page.content.is_filed"/>
        <br/>
        <fmt:message key="error_page.content.servlet_name"/>
        ${pageContext.errorData.servletName}
        <br/>
        <fmt:message key="error_page.content.status_code"/>
        ${pageContext.errorData.statusCode}
        <br/>
        <fmt:message key="error_page.content.exception"/>
        ${pageContext.errorData.throwable}
  </div>

<button onclick="window.location.href='${pageContext.request.contextPath}/index.jsp'">
    <fmt:message key="error_page.back_button"/>
</button>

</body>
</html>
