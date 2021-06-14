<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="MyResources"/>
<html>
<head>
    <title><fmt:message key="header.login"/></title>
    <meta content="text/html; charset=UTF-8"/>

</head>
<body>
<h1><fmt:message key="header.login"/></h1>
<div class="nav col-lg-12">
    <span>
        <a href="${pageContext.request.contextPath}/restaurant/main"><fmt:message key="header.main"/></a>
            <a class="nav-link" href="${pageContext.request.contextPath}/restaurant/registration"><fmt:message
                    key="header.registration"/></a>
    </span>
    <a href="${pageContext.request.contextPath}?sessionLocale=en">ENG</a>
    <a href="${pageContext.request.contextPath}?sessionLocale=ru">RU</a>
</div>
<div class="form col-lg-12">
    <form name="form" autocomplete="off"
          action="${pageContext.request.contextPath}/restaurant/login/Post" method="post">
        <p>${loginStatus}</p>
        <fmt:message key="label.login" var="label_log"/>
        <label text="Login" for="login">${label_log}</label><br>
        <input type="text"
               required
               name="login"
               id="login"
               placeholder="${label_log}"/>
        <br>
        <fmt:message key="label.password" var="label_pass"/>
        <label text="Password" for="password">${label_pass}</label><br>
        <input type="password"
               id="password"
               name="password"
               required
               placeholder="${label_pass}"/>
        <input class="sbm-btn" type="submit" value="Submit"/>
    </form>
</div>
</body>
</html>
