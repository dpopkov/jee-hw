<%--@elvariable id="statMarkers" type="java.util.List<ru.otus.dpopkov.webstats.model.StatMarker>"--%>
<html>
<head>
    <title>Show Statistics</title>
</head>
<body>

<h3>Show Statistics</h3>

<ul>
    <c:forEach items="${statMarkers}" var="sm">
        <li>${sm}</li>
    </c:forEach>
</ul>

</body>
</html>
