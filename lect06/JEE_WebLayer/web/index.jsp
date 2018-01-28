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
                    <header>
                        <h2>Новости</h2>
                        <table>
                            <tr>
                                <td><a href="#news1">C comes back</a></td>
                                <td><a href="#news2">Java JDK 10 new features</a></td>
                                <td><a href="#news3">Replacement for Java EE</a></td>
                            </tr>
                        </table>
                    </header>
                    <section id="news1">
                        <h3>C completes comeback in programming popularity</h3>
                        <p>The once-declining C language has completed a comeback in the monthly Tiobe Index of language popularity, winning the 2017 Programming Language of the Year designation from Tiobe as the biggest gainer in share.
                            <a href="https://www.javaworld.com/article/3247787/application-development/c-language-completes-comeback-in-programming-popularity.html">read...</a>
                        </p>
                    </section>
                    <section id="news2">
                        <h3>Java JDK 10: What new features to expect in the next Java</h3>
                        <p>Developers who may be just getting used to Java 9, released in September 2017, will have only a few months left before the next generation of Java is out. In mid-December, the planned Java Development Kit 10 upgrade moved to a rampdown phase. In the initial rampdown phase, only P1 through P3 bugs can be fixed.
                            <a href="https://www.javaworld.com/article/3243693/java-language/java-jdk-10-what-new-features-to-expect-in-the-next-java.html">read...</a>
                        </p>
                    </section>
                    <section id="news3">
                        <h3>EE4J: Eclipse’s replacement for Java EE unveiled</h3>
                        <p>The Eclipse Foundation, the new keeper of enterprise Java, has moved forward with nine project proposals for Eclipse Enterprise for Java (EE4J), which the organization describes as the first step toward the migration of Java EE (Enterprise Edition) to the open source tools organization.
                        <a href="https://www.javaworld.com/article/3238151/enterprise-java/eclipse-begins-enterprise-java-transition-from-java-ee-to-ee4j.html">read...</a></p>
                    </section>
                    <footer>
                        <table>
                            <tr>
                                <td><a href="#news1">C comes back</a></td>
                                <td><a href="#news2">Java JDK 10 new features</a></td>
                                <td><a href="#news3">Replacement for Java EE</a></td>
                            </tr>
                        </table>
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
