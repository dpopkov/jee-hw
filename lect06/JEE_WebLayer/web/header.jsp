<%@ page contentType="text/html;charset=UTF-8" %>

<header>
    <!-- Шапка сайта: логотип, элемент поиска, общая информация об авторе или телефон организации (меню) -->
    <table>
        <tr>
            <td>
                <img src="img/logo.png" />
            </td>
            <td>
                <form>
                    <input type="text" name="search" placeholder="строка для поиска"/>
                </form>
            </td>
            <td>
                +7 495 123-45-67
            </td>
        </tr>
    </table>

    <!-- Горизонтальное меню (ссылки на основные разделы сайта) -->
    <table>
        <tr>
            <td><a href="index.jsp">Главная</a></td>
            <td><a href="login.jsp">Вход в систему</a></td>
            <td><a href="new-idea.jsp">Idea news</a></td>
            <td><a href="new-eclipse.jsp">Eclipse news</a></td>
            <td><a href="run-js.jsp">Run JavaScript</a></td>
        </tr>
    </table>

</header>
