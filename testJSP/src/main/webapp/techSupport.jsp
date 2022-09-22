<%-- 
    Document   : techSupport
    Created on : 19 sept. 2022, 08:39:49
    Author     : antoine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="techSupportBean" scope="session" class="test.testjsp.TechSupportBean"/>
<jsp:setProperty name="techSupportBean" property="*"/>
<% String email = request.getParameter("email");
   techSupportBean.setClient(email);
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% if (techSupportBean.getClient().isIsRegistered()) { 
                techSupportBean.register();
        %>
            <jsp:forward page="response.jsp"/>
        <% } else { %>
            <jsp:forward page="regform.jsp"/>
        <% } %>
    </body>
</html>
