<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Новостная система</title>
    <link rel="stylesheet" href="css/style.css"/>
    <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.5.min.js" type="text/javascript"></script>
</head>
<body>

<div class="body-wrapper">
    <table>
        <tr>
            <td colspan="2">
                <%@ include file="header.jsp"%>
            </td>
        </tr>

        <tr>
            <td>
            <!-- Блок основного контента -->
                <table>
                    <tr>
                        <td valign="top">JavaScript:</td>
                        <td><textarea id="inputScript" rows="10" cols="30"></textarea></td>
                    </tr>
                    <tr>
                        <td valign="top">Result:</td>
                        <td><textarea id="scriptResult" rows="10" cols="30"></textarea></td>
                    </tr>
                </table>

                <script>
                    function makeRequest (method, url, done) {
                        var xhr = new XMLHttpRequest();
                        xhr.open(method, url);
                        xhr.onload = function () {
                            done(null, xhr.response);
                        };
                        xhr.onerror = function () {
                            done(xhr.response);
                        };
                        xhr.send();
                    }
                    var inputScript = document.getElementById("inputScript");
                    inputScript.addEventListener("blur", function() {
                        makeRequest('GET', 'js?code=' + inputScript.value,
                            function (err, datums) {
                                if (err) { throw err; }
                                $('#scriptResult').val(datums);
                            });
                    });
                </script>
            </td>

            <td>
                <%@ include file="sidebar.jsp"%>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <%@ include file="footer.jsp"%>
            </td>
        </tr>
    </table>

</div>

</body>
</html>
