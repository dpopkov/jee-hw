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
                        <h2>Eclipse Oxygen - New and Noteworthy</h2>
                    </header>
                    <section>
                        <h3>New features in the Platform and Equinox</h3>

                        <h4>Editors</h4>
                        <p>A new extensible Generic Editor has been added which makes it easier to implement an editor for new languages or new content types. It reuses the existing Eclipse editor infrastructure. The generic editor can be extended using extension points instead of implementing an editor to supply required functionality.</p>
                        <p>An extension was provided to the generic editor so that it now can provide syntax highlighting for files with the .patch or .diff extension. Right-click the file and choose Open with > Generic Text Editor to see the editor in action.</p>
                        <p>Image files are now opened directly in Eclipse by default. The Internal Web Browser has been registered as default editor for files with the png, jpg, jpeg, gif, bmp, and ico extensions.</p>
                        <p>The Editor Selection dialog now offers a one-click option for choosing the editor that should open all files with the same extension in the future.</p>

                        <h4></h4>
                    </section>
                    <section>
                        <h3>New features for Java developers</h3>

                        <h4>Java Editor</h4>
                        <p>The Open Implementation hyperlink and the Navigate > Open Implementation action now open the implementation of the selected interface or class also. In the past, Open Implementation was only available for methods.</p>
                        <p>The Java > Editor > Typing > Automatically insert at correct position > Braces preference option is now enabled by default. This will automatically insert the braces where they are required.</p>
                        <p>The Java > Editor > Typing > Escape text when pasting into a string literal preference option is now enabled by default. This will escape the special characters in pasted strings when they are pasted into an existing string literal.</p>
                    </section>
                    <footer>
                        <p>Source: <a href="https://www.eclipse.org/community/news/">Eclipse News</a></p>
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
