<%--@elvariable id="users" type="java.util.List<ru.otus.dpopkov.webstats.model.User>"--%>
<html>
<head>
    <title>Users</title>
</head>
<body>

<h3>Users</h3>

<ul>
    <c:forEach items="${users}" var="user">
        <li>Login: ${user.name}, password: ${user.password}</li>
    </c:forEach>
</ul>

<jee:saveStats/>

</body>
</html>
