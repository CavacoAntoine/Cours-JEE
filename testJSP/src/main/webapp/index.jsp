<%-- 
    Document   : index
    Created on : 19 sept. 2022, 08:22:16
    Author     : antoine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <HEAD>
        <TITLE>Site de maintenance</TITLE>
    </HEAD>
    <BODY>
        <H1>Support technique</H1>
        <HR><BR>
        <CENTER>
            <FORM ACTION="/testJSP/techSupport.jsp" METHOD="POST">
                <TABLE ALIGN="center" WIDTH="100%" CELLSPACING="2" CELLPADDING="2">                        
                    <TR>
                        <TD ALIGN="right">Email:</TD>
                        <TD><INPUT TYPE="Text" NAME="email" ALIGN="LEFT" SIZE="25"></TD>
                    </TR>
                    <TR>
                        <TD ALIGN="right">Logiciel:</TD>
                        <TD>
                            <SELECT NAME="software" SIZE="1">
                                <OPTION VALUE="Word">Microsoft Word</OPTION>
                                <OPTION VALUE="Excel">Microsoft Excel</OPTION>
                                <OPTION VALUE="Access">Microsoft Access</OPTION>
                            </SELECT>
                        </TD>
                        <TD ALIGN="right">Systeme d'exploitation:</TD>
                        <TD>
                            <SELECT NAME="os" size="1">
                                <OPTION VALUE="XP">Windows XP</OPTION>
                                <OPTION VALUE="Linux">Linux</OPTION>
                                <OPTION VALUE="MAC OS">Mac OS</OPTION>
                            </SELECT>
                        </TD>
                    </TR>
                </TABLE>
                <BR>Description du probleme
                <BR>
                <TEXTAREA NAME="problem" COLS="50" ROWS="4"></TEXTAREA>
                <HR><BR>
                <INPUT TYPE="Submit" NAME="submit" VALUE="Soummettre la requete">
            </FORM>
        </CENTER>
    </BODY>
</html>
