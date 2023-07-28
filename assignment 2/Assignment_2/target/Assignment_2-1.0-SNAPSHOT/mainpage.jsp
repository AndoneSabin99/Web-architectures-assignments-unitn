<%@ page import="it.unitn.disi.webarch.sabinandone.utilities.UserBean" %><%--
  Created by IntelliJ IDEA.
  User: Sabin
  Date: 12/10/2022
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Start Page</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div class="container"><h1>Welcome! Please press the "play" button to start.</h1>
  <br/>
  <%
    UserBean u = (UserBean)session.getAttribute("UserBean");
    Integer points = (Integer)session.getAttribute("points");
  %>
  <p>Points: <%=points%></p>

  <form action="http://localhost:8080/Assignment_2/PrepareServlet" method="GET">
    <input type="submit" value="Play">
  </form>
</div>
</body>
</html>