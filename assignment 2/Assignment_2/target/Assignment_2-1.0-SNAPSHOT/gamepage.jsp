<%@ page import="java.util.ArrayList" %>
<%@ page import="it.unitn.disi.webarch.sabinandone.utilities.FlagBean" %><%--
  Created by IntelliJ IDEA.
  User: Sabin
  Date: 12/10/2022
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Game Page</title>
</head>
<body>
<div class="container d-flex align-items-right">
    <div class="card" >
        <div class="card-body">
            <%
                ArrayList<String> capitals = new ArrayList<String>();
                capitals = (ArrayList<String>)session.getAttribute("capitals");
                FlagBean first = (FlagBean) session.getAttribute("firstFlag");
                FlagBean second = (FlagBean) session.getAttribute("secondFlag");
                FlagBean third = (FlagBean) session.getAttribute("thirdFlag");

                for(int i=0; i<10; i++){
            %>
            <p><%=(i+1)%>, <%=(capitals.get(i))%></p>
            <%}%>
        </div>
    </div>

    <div class="card">
        <div class="card-body">
            <form action="http://localhost:8080/Assignment_2/UpdateServlet" method="GET">
                <img class="card-img-top" src="${pageContext.request.contextPath}/resources/flags/<%=first.getNation()%>" width="100" alt="flag">
                <select class="form-control" name="firstValue" style="width: 75px;" required>
                    <%for(int i=1; i<11; i++){%>
                        <option value="<%=i%>"><%=i%></option>
                    <%}%>
                </select>
                <img class="card-img-top" src="${pageContext.request.contextPath}/resources/flags/<%=second.getNation()%>" width="100" alt="flag">
                <select class="form-control" name="secondValue" style="width: 75px;" required>
                    <%for(int i=1; i<11; i++){%>
                    <option value="<%=i%>"><%=i%></option>
                    <%}%>
                </select>
                <img class="card-img-top" src="${pageContext.request.contextPath}/resources/flags/<%=third.getNation()%>" width="100" alt="flag">
                <select class="form-control" name="thirdValue" style="width: 75px;" required>
                    <%for(int i=1; i<11; i++){%>
                    <option value="<%=i%>"><%=i%></option>
                    <%}%>
                </select>
                <input type="submit" class="btn btn-primary" value="Submit query">
            </form>
        </div>
    </div>

</div>
</body>
</html>
