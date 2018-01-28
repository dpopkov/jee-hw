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
                        <h2>What’s New in IntelliJ IDEA 2018.1 EAP?</h2>
                    </header>
                    <section>
                        <p>Last week we opened the IntelliJ IDEA 2018.1 Early Access Program, and we hope you have already given this new version a try! Today we’re publishing a brand new IntelliJ IDEA EAP build. Download the latest EAP build via Toolbox App or get a copy from our website. Read this blog post to learn about all the new features this EAP build has prepared for you.</p>
                        <p>The improvements are coming for the Spring Boot framework. Now, after you run a Spring Boot web application, a new icon is shown in the gutter for methods with @RequestMapping annotations that handle incoming HTTP requests. By simply clicking  this gutter icon you can open all mappings in a scratch file with an .http extension and perform an HTTP request in the editor via the new REST client.</p>
                        <p>For methods with @GetMapping annotations, you have a choice to open the mapped URLs in a browser, or open a request in the HTTP Request Editor.</p>
                        <h4>Version Control Systems</h4>
                        <p>In the Log tab inside the commit detail panes, the IDE now highlights hashes of the commits you are referring to. By simply clicking commit hashes you can jump to that commit in the Log tab. This works for Git and Mercurial</p>
                        <p>For Git integration, IntelliJ IDEA lets you view detailed information about file changes by invoking the Show History for Revision action on a  file in the Log tab. We have updated the History for revision tab, and now it works much faster. We’ve also changed the UI and made it look more like the Log tab.</p>
                        <p>The upcoming IntelliJ IDEA 2018.1 introduces the new Enable Smart zooming feature that fits images into the window size. Previously, the IDE only increased the size of small graphics to preview even tiny icons. Now IntelliJ IDEA can both decrease and increase  image size, and preview the whole image in the editor. The Enable Smart zooming option is enabled by default, and the IDE will automatically resize graphics to fit them into the window. This option is available inside Preferences | Editor | Images.</p>
                        <p><a href="https://blog.jetbrains.com/idea/2018/01/whats-new-in-intellij-idea-2018-1-eap-4/">Read full article</a></p>
                    </section>
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
