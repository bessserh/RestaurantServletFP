<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="MyResources"/>

<%@ taglib uri="/WEB-INF/userShow.tld" prefix="name" %>

<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="header.adminPage"/></title>
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
            <a class="navbar-brand" href="/" style="font-size: 36px"><fmt:message key="header.adminPage"/>: </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div style="background-color: bisque"><name:userName  name="${name}"/></div>

            <div class="collapse navbar-collapse" id="navbarCollapse" style="margin-left: 30%;">

                <a href="${pageContext.request.contextPath}/restaurant/main"
                   class="btn btn-outline-success" style="background-color: cornflowerblue"><fmt:message key="header.main"/></a>

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
    <div class="table table-bordered table-sm table-striped">
        <!--orders ACTIVE-->
        <div class="alert alert-primary" role="alert"
             style="text-align: center; font-size: large"><fmt:message key="table.active"/></div>
        <table>
            <thead class="thead-dark">
            <tr>
                <th>ID</th>
                <th>Status</th>
                <th>User</th>
                <th>Creation</th>
                <th>Update</th>
                <th>Next</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${orders}">
                <c:if test="${order.status == 'APPROVING'
                || order.status == 'COOKING' || order.status == 'DELIVERING'}">
                <tr>                
                    <td>${order.id}</td>
                    <td>${order.status}</td>
                    <td>${order.userId}</td>
                    <td>${order.creationDate}</td>
                    <td>${order.updateDate}</td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/restaurant/admin/Post">
                            <input type='hidden' id='userId' name='userId' value=${order.userId}>
                            <input type="submit" value="<fmt:message key="button.nextStatus"/>"/>
                        </form>
                    </td>               
                </tr>
                </c:if>
            </c:forEach>
            </tbody>
        </table>
        <!--orders DONE-->
        <div class="alert alert-success" role="alert"
             style="text-align: center; font-size: large"><fmt:message key="table.history"/></div>
        <table>
            <thead class="thead-dark">
            <tr>
                <th>ID</th>
                <th>Status</th>
                <th>User</th>
                <th>Creation</th>
                <th>Update</th>
                <th>Next</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${orders}">
                <c:if test="${order.status == 'CANCELLED'
                || order.status == 'CLOSED'}">
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.status}</td>
                        <td>${order.userId}</td>
                        <td>${order.creationDate}</td>
                        <td>${order.updateDate}</td>
                        <td>
                            <h5><fmt:message key="all.done"/> </h5>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>
        </table>
    </div>
</main>
</body>
</html>