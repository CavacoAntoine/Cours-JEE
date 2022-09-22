<%-- 
    Document   : regform
    Created on : 22 sept. 2022, 18:26:03
    Author     : antoine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="/error.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Customer Registration</title>
    </head>
    <body>
    <center><h1>Customer Registration</h1></center>
    <hr>
    Please register.
    <form action="/testJSP/register.jsp" method="POST">
        <table>
            <tr>
                <td>First Name:</td>
                <td><input type="text" name="firstName" size="30"></td>
            </tr>
            <tr>
                <td>Last Name:</td>
                <td><input type="text" name="lastName" size="30"></td>
            </tr>
            <tr>
                <td>Phone Number:</td>
                <td><input type="text" name="phoneNumber" size="30"></td>
            </tr>
        </table>
        <br><input type="submit" value="Submit Request">
    </form>
</body>
</html>
