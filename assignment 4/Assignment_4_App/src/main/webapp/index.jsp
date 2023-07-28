<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Start Page</title>
</head>
<body>
<h1><%= "Hello! Insert the matriculation number of a student to see data about that student." %>
</h1>
<br/>
<form action="/StudentServlet" method="get">
    <label for="matr">Matriculation number:</label><br>
    <input type="text" id="matr" name="matr" placeholder="xxxxxx"><br><br>
    <input type="submit" value="Student page">
    <input type="submit" value="Advisor page" formaction="/AdvisorPageServlet">
</form>
</body>
</html>