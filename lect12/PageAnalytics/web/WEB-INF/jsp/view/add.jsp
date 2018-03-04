<html>
<head>
    <title>Add User</title>
</head>
<body>

<h3>Add User</h3>
<form method="post" action="<c:url value="/dbservlet" />">
    <input type="hidden" name="action" value="add" />
    Login:<br/>
    <input type="text" name="login" /><br/>
    Password:<br/>
    <input type="password" name="password" /><br/>
    <input type="submit" value="Submit" />
</form>

<jee:saveStats/>

</body>
</html>
