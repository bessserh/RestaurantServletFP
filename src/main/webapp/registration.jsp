<%--
  Created by IntelliJ IDEA.
  User: BesUlya
  Date: 06.05.2021
  Time: 12:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>register</title>
</head>
<body>
<form name="form" class="form" autocomplete="off"
      action="${pageContext.request.contextPath}/restaurant/registration/Post" method="post">

    <label text="Login" for="login"></label><br>
    <input type="text"
           required
           name="login"
           id="login"
           placeholder="${label_log}"/>
    <br>

    <label text="Password" for="password">${label_pass}</label><br>
    <input type="password"
           id="password"
           name="password"
           required
           placeholder="${label_pass}"/>
    <input class="sbm-btn" type="submit" value="Submit"/>
</form>
</body>
</html>
