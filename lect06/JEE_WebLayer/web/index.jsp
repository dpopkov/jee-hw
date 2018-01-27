<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Новостная система</title>
</head>
<body>

<div>
    <header>
        <!-- Шапка сайта: логотип, элемент поиска, общая информация об авторе или телефон организации (меню) -->
        <div>
            <div>
                Logo
            </div>
            <div>
                Поиск
            </div>
            <div>
                Меню
            </div>
        </div>

        <!-- Горизонтальное меню (ссылки на основные разделы сайта) -->
        <nav>
            <ul>
                <li>Главная</li>
                <li>Вход в систему</li>
                <li>link 1</li>
                <li>link 2</li>
                <li>link 3</li>
            </ul>
        </nav>
    </header>

    <!-- Блок основного контента -->
    <main>
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
    </main>

    <!-- Боковое меню -->
    <aside>
        <section>
            <h2>Курсы валют</h2>
            <div id="currencies">
                <jsp:include page="/currencies"/>
            </div>
        </section>
        <section>
            <h2>Новости</h2>
            <p>новости новости новости новости новости новости новости новости новости новости новости </p>
        </section>
    </aside>

    <!-- "Подвал" сайта -->
    <footer>
        <small>Copyright ©
            <time datetime="2018">2018</time>
            thebestnewsru.ru
        </small>
    </footer>
</div>

</body>
</html>
