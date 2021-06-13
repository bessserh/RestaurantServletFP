<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="MyResources"/>

<html lang="en">
<head>

  <meta charset="UTF-8">
  <title><fmt:message key="header.restaurant"/></title>

  <!-- Bootstrap core CSS -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>


</head>
<body>

<header>
  <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <div class="container-fluid">
      <a class="navbar-brand" href="/" style="font-size: 36px"><fmt:message key="header.restaurant"/></a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarCollapse" style="margin-left: 40%;">

        <c:if test="${role!='USER' && role!='ADMIN'}">
          <a class="nav-link" href="${pageContext.request.contextPath}/restaurant/login"><fmt:message key="header.login"/></a>
          <a class="nav-link" href="${pageContext.request.contextPath}/restaurant/registration"><fmt:message key="header.registration"/></a>
        </c:if>
        <c:if test="${role=='USER' || role=='ADMIN'}">
          <a class="nav-link" href="${pageContext.request.contextPath}/restaurant/logout"><fmt:message key="header.logout"/></a>
        </c:if>
        <c:if test="${role=='USER'}">
          <a class="nav-link" href="${pageContext.request.contextPath}/restaurant/userCabinet"><fmt:message key="header.userCabinet"/></a>
        </c:if>
        <c:if test="${role=='ADMIN'}">
          <a class="nav-link" href="${pageContext.request.contextPath}/restaurant/admin"><fmt:message key="header.adminPage"/></a>
        </c:if>


        <div style="margin-left: 10%">
          <select name="sessionLocale" onchange="submit()">
            <option value="en"><fmt:message key="language.en"/></option>
            <option value="uk"><fmt:message key="language.ru"/></option>
          </select>
        </div>

      </div>
    </div>
  </nav>
</header>

<main style="margin-top: 7%">

  <span><fmt:message key="label.sort"/>:</span>
  <a href="${pageContext.request.contextPath}/restaurant/main?page=${currentPage}&sortField=price&sortDir=${sortDirReversed}&choose=${choose}"><fmt:message key="sort.price"/></a>
  <a href="${pageContext.request.contextPath}/restaurant/main?page=${currentPage}&sortField=name&sortDir=${sortDirReversed}&choose=${choose}"><fmt:message key="sort.name"/></a>
  <br>
  <a href="${pageContext.request.contextPath}/restaurant/main?page=1&sortField=${sortField}&sortDir=${sortDir}&choose=all"><fmt:message key="category.all"/></a>
  <a href="${pageContext.request.contextPath}/restaurant/main?page=1&sortField=${sortField}&sortDir=${sortDir}&choose=first"><fmt:message key="category.first"/></a>
  <a href="${pageContext.request.contextPath}/restaurant/main?page=1&sortField=${sortField}&sortDir=${sortDir}&choose=drinks"><fmt:message key="category.drinks"/></a>
  <a href="${pageContext.request.contextPath}/restaurant/main?page=1&sortField=${sortField}&sortDir=${sortDir}&choose=pizza"><fmt:message key="category.pizza"/></a>
  <a href="${pageContext.request.contextPath}/restaurant/main?page=1&sortField=${sortField}&sortDir=${sortDir}&choose=sushi"><fmt:message key="category.sushi"/></a>
  <a href="${pageContext.request.contextPath}/restaurant/main?page=1&sortField=${sortField}&sortDir=${sortDir}&choose=dessert"><fmt:message key="category.dessert"/></a>


    <div class="menu">
      <c:forEach var="dish" items="${dishesList}">
        <div class="dish col-lg-4">
          <img src="/images/${dish.image}" alt="dish img"/>
          <h3>${dish.name}</h3>
          <p><fmt:formatNumber value="${dish.price}" maxFractionDigits="2"/></p>
          <div class="order-btn">
            <form action="${pageContext.request.contextPath}/restaurant/userCabinet/Post" method="post">
              <input type='hidden' id='DishId' name='DishId' value='${dish.id}'>
              <button type="submit"><fmt:message key="button.order"/></button>
            </form>
          </div>
        </div>
      </c:forEach>
    </div>

    <c:if test="${noOfPages > 1}">
      <div class="col-lg-12">

        <c:if test="${currentPage != 1}">
          <span><a href="${pageContext.request.contextPath}/restaurant/main?page=${currentPage - 1}&sortField=${sortField}&sortDir=${sortDir}&choose=${choose}"><fmt:message key="page.previous"/></a></span>
        </c:if>

        <c:forEach begin="1" end="${noOfPages}" var="i">
          <c:choose>
            <c:when test="${currentPage == i}">
              <span class="disabled">${i}</span>
            </c:when>
            <c:otherwise>
              <span><a href="${pageContext.request.contextPath}/restaurant/main?page=${i}&sortField=${sortField}&sortDir=${sortDir}&choose=${choose}">${i}</a></span>
            </c:otherwise>
          </c:choose>
        </c:forEach>

        <c:if test="${currentPage < noOfPages}">
          <span><a href="${pageContext.request.contextPath}/restaurant/main?page=${currentPage + 1}&sortField=${sortField}&sortDir=${sortDir}&choose=${choose}"><fmt:message key="page.next"/></a></span>
        </c:if>
      </div>
    </c:if>
</main>
</body>
</html>