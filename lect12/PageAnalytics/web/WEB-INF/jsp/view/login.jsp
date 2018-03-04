<%--@elvariable id="loginFailed" type="boolean"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<h3>Login</h3>

<p>Enter any login/password pair for now...</p>
<c:if test="${loginFailed == true}">
    <p>Login failed</p>
</c:if>
<form action="<c:url value="/login" />" method="post">
    Login:<br/>
    <input type="text" name="login" /><br/>
    Password:<br/>
    <input type="password" name="password" /><br/>
    <input type="submit" value="Login" />
</form>

<jee:saveStats/>

</body>
</html>
