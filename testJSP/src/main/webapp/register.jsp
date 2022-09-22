<%-- 
    Document   : register
    Created on : 19 sept. 2022, 14:54:40
    Author     : antoine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="/error.jsp" %>
<jsp:useBean id="client" scope="session" class="test.testjsp.Client"/>
<jsp:useBean id="techSupportBean" scope="session" class="test.testjsp.TechSupportBean"/>
<jsp:setProperty name="client" property="*"/>
<% 
    client.setEmail(techSupportBean.getClient().getEmail());
    client.setIsRegistered(false);
    techSupportBean.setClient(client);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        Traitement...
        <% 
           client.register(); 
           techSupportBean.register();
        %> 
        <jsp:forward page="response.jsp"/>
    </body>
</html>
