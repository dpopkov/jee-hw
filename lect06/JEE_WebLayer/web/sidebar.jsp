<%@ page contentType="text/html;charset=UTF-8" %>
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
        <div id="news">
            <jsp:include page="/rbc"/>
        </div>
    </section>
</aside>
