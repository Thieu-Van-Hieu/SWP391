<%-- 
    Document   : index
    Created on : Apr 30, 2025, 10:56:41 AM
    Author     : ngoct
--%>

<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    </head>
    <body>
    <center>
        <form action="login" method="post">
            <table>
                <tr>
                    <td>Username:</td>
                    <td><input type="text" name="username"></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password"></td>
                </tr>
                <tr>
                    <td><input type="submit" name="name"></td>
                    <td><a href="${pageContext.request.contextPath}/view/register.jsp">register</a></td>
                </tr>
            </table>
        </form>
    </center>
</body>
</html> 
