<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>



<%@ page isELIgnored="false" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="MyResources"/>

<%@ taglib uri="/WEB-INF/userShow.tld" prefix="name" %>

<html>
<head>
    <title><fmt:message key="header.userCabinet"/></title>
    <meta content="text/html; charset=UTF-8"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cards.css" />
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>


</head>
<header>
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="/" style="font-size: 36px"><fmt:message key="header.userCabinet"/>: </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div style="background-color: aqua"><name:userName  name="${name}"/></div>

        <div class="collapse navbar-collapse" id="navbarCollapse" style="margin-left: 40%;">

            <a href="${pageContext.request.contextPath}/restaurant/main"
               class="btn btn-outline-success" style="background-color: blue"><fmt:message key="header.main"/></a>

            <div style="margin-left: 10%">
                <a href="${pageContext.request.contextPath}?sessionLocale=en"
                   class="btn btn-outline-success" style="background-color: blue"><fmt:message key="language.en"/></a>
                <a href="${pageContext.request.contextPath}?sessionLocale=ru"
                   class="btn btn-outline-success" style="background-color: darkred"><fmt:message key="language.ru"/></a>
            </div>
        </div>
    </div>
</nav>
</header>

<main style="margin-top: 7%">

<div class="cardCont" style="margin-top: 2%">
    <c:if test="${dishes!=null}">
        <c:forEach var="dish" items="${dishes}">
        <div class="card eachCard" style="width: 21rem;">
            <img class="card-img-top imageShow" src="/images/${dish.image}" alt="dish img"/>
            <div class="dish-body">
                <div class="card-body">
                    <span>${dish.name}</span>
                    <span><fmt:formatNumber value="${dish.price}" maxFractionDigits="2"/></span>
                    <span>${dish.amount}</span>
                    <span><fmt:formatNumber value="${dish.totalPrice}" maxFractionDigits="2"/></span>

                    <c:if test="${orderStatus=='MAKING'}">
                    <div class="dish-nav">
                        <form class="dish-cab-form" action="${pageContext.request.contextPath}/restaurant/manageOrder"
                              method="post">
                            <input type='hidden' id='amountIncrease' name='manageOrder' value='plus'>
                            <input type='hidden' id='dishId1' name='dishId' value='${dish.id}'>
                            <input type='hidden' id='dishPrice1' name='dishPrice' value='${dish.price}'>
                            <input type='hidden' id='orderId1' name='orderId' value='${orderId}'>
                            <button type="submit">+</button>
                        </form>
                        <form class="dish-cab-form" action="${pageContext.request.contextPath}/restaurant/manageOrder"
                              method="post">
                            <input type='hidden' id='amountDecrease' name='manageOrder' value='minus'>
                            <input type='hidden' id='dishId2' name='dishId' value='${dish.id}'>
                            <input type='hidden' id='dishPrice2' name='dishPrice' value='${dish.price}'>
                            <input type='hidden' id='orderId2' name='orderId' value='${orderId}'>
                            <input type='hidden' id='dishAmount' name='dishAmount' value='${dish.amount}'>
                            <button type="submit">-</button>
                        </form>
                        <form class="dish-cab-form" action="${pageContext.request.contextPath}/restaurant/manageOrder"
                              method="post">
                            <input type='hidden' id='RemoveDish' name='manageOrder' value='remove'>
                            <input type='hidden' id='dishId3' name='dishId' value='${dish.id}'>
                            <input type='hidden' id='dishPrice3' name='dishPrice' value='${dish.totalPrice}'>
                            <input type='hidden' id='orderId3' name='orderId' value='${orderId}'>
                            <button type="submit"><fmt:message key="button.removeDish"/></button>
                        </form>
                </div>
            </c:if>
            </div>
            </div>
        </div>
        </c:forEach>
</div>
    <div>
        <c:if test="${dishes.size() > 0}">

            <c:if test="${orderStatus=='MAKING'}">
                <form action="${pageContext.request.contextPath}/restaurant/userCabinet/Pay" method="post">
                    <button type="submit"><fmt:message key="button.pay"/></button>
                </form>
            </c:if>
        </c:if>
        <c:if test="${orderStatus=='APPROVING'}">
            <form action="${pageContext.request.contextPath}/restaurant/userCancelOrder" method="post">
                <input type='hidden' id='order' name='orderId' value='${orderId}'>
                <button type="submit"><fmt:message key="button.cancel"/></button>
            </form>
        </c:if>
        <c:if test="${orderStatus!='MAKING'}">
            <h2><fmt:message key="cabinet.order.status"/>: ${orderStatus}</h2>
        </c:if>

    </div>
</c:if>
</main>
</body>
</html>