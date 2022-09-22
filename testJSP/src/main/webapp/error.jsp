<%-- 
    Document   : error
    Created on : 22 sept. 2022, 18:29:00
    Author     : antoine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page isErrorPage="true" %>
<html>
    <head>
        <TITLE>Site de maintenance</TITLE>
    </head>
    <body>
        <h1>Technical Support</h1>
        <p>We're sorry, an error occurred processing your request.</p>
        <p>You got a <%= exception%>
    </body>
</html>
