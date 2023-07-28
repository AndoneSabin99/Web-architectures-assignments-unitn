<%@ page import="it.unitn.disi.webarch.sabinandone.utilities.UserBean" %><%--
  Created by IntelliJ IDEA.
  User: Sabin
  Date: 12/10/2022
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <!-- Bootstrap link -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<%
  UserBean u = (UserBean)session.getAttribute("UserBean");
%>

<nav class="navbar navbar-expand-sm bg-dark">
  <ul class="navbar-nav">
    <li class="nav-item">
      <p class="text-light">User: <%=u.getUser()%></p>
    </li>
  </ul>
</nav> <br>
</body>
</html>
