<%--
  Created by IntelliJ IDEA.
  User: Sabin
  Date: 12/10/2022
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>login</title>
    <!-- Bootstrap link -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h1>Authentication: </h1>
    <div class="text-danger">
        <%String message = (String)request.getAttribute("message");
            if (message!=null){
                out.print("<p>" + message + "</p>");
            }
        %>
    </div>
    <form action="http://localhost:8080/Assignment_2/LoginServlet" method="post">
        <div class="form-group">
            <label for="usr">Username:</label>
            <input type="text" class="form-control" id="usr" placeholder="username" name="username">
        </div>
        <div class="form-group">
            <label for="pwd">Password:</label>
            <input type="password" class="form-control" id="pwd" placeholder="password" name="password">
        </div>
        <button type="submit" class="btn btn-primary">Submit query</button>
    </form>
    <br>
    <a href="http://localhost:8080/Assignment_2/registration.jsp">Click here to register</a>
</div>
</body>
</html>
