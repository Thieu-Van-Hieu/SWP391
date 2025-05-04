<%-- 
    Document   : register
    Created on : Apr 30, 2025, 10:58:12 AM
    Author     : ngoct
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center>
        <form action="${pageContext.request.contextPath}/register" method="post">
            <table>
                <tr>
                    <td>Username:</td>
                    <td><input type="text" name="user_name"></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password"></td>
                </tr>
                <tr>
                    <td>Again Password:</td>
                    <td><input type="password" name="againpassword"></td>
                </tr>
                <tr>
                    <td>Your Email:</td>
                    <td><input type="text" name="email"></td>
                </tr>
                <tr>
                    <td><input type="submit" name="name"></td>
                </tr>
            </table>
        </form>
    </center>
</body>
</html>
