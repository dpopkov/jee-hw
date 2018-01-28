<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Новостная система</title>
    <link rel="stylesheet" href="css/style.css"/>
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
                <section>
                    <h2>Вход с систему</h2>
                    <form>
                        <table>
                            <tr>
                                <td><label for="loginField">Логин: </label></td>
                                <td><input id="loginField" type="text" name="login"/></td>
                            </tr>
                            <tr>
                                <td><label for="passwordField">Пароль: </label></td>
                                <td><input id="passwordField" type="password" name="password"/></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><input type="submit" value="Войти"/></td>
                            </tr>
                        </table>
                    </form>
                </section>
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
