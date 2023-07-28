<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: Sabin
  Date: 14/10/2022
  Time: 23:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Control Page</title>
</head>
<body>
<%
    HashMap<String,Integer> connectedUserlist = new HashMap<>();
    connectedUserlist = (HashMap<String,Integer>)session.getAttribute("connectedUserlist");
%>

<div class="container">
    <table class="table">
        <thead class="thead-dark">
            <tr>
                <th scope="col">User</th>
                <th scope="col">Points</th>
            </tr>
        </thead>
        <tbody>
            <%for(Map.Entry<String, Integer> entry : connectedUserlist.entrySet()){%>
                <tr scope="row">
                    <td><%=entry.getKey()%></td>
                    <td><%=entry.getValue()%></td>
                </tr>
            <%}%>
        </tbody>
    </table>
</div>

</body>
</html>
