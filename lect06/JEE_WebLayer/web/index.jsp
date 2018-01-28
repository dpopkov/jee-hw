<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Новостная система</title>
    <link rel="stylesheet" href="css/style.css"/>
</head>
<body>

<div class="body-wrapper">

    <table border="1">
        <tr>
            <td colspan="2">
                <%@ include file="header.jsp"%>
            </td>
        </tr>

        <tr>
            <td>
            <!-- Блок основного контента -->
                <section>
                    <header>
                        <h2>Новости</h2>
                        <nav>
                            <ul>
                                <li>news 1</li>
                                <li>news 2</li>
                                <li>news 3</li>
                            </ul>
                        </nav>
                    </header>
                    <section>
                        <h3>News 1</h3>
                        <p>Info</p>
                    </section>
                    <section>
                        <h3>News 2</h3>
                        <p>Info</p>
                    </section>
                    <section>
                        <h3>News 2</h3>
                        <p>Info</p>
                    </section>
                    <footer>
                        Links to: news 1 | news 2 | news 3
                    </footer>
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
