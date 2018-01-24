<xsl:stylesheet
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/ValCurs">
        <html>
            <head>
                <title>Foreign Currency Market</title>
            </head>
            <body>
                <table>
                    <tr>
                        <th>Code</th>
                        <th>Name</th>
                        <th>Value</th>
                    </tr>
                    <xsl:for-each select="Valute">
                        <tr>
                            <td><xsl:value-of select="CharCode"/></td>
                            <td><xsl:value-of select="Name"/></td>
                            <td><xsl:value-of select="Value"/></td>
                        </tr>
                    </xsl:for-each>
                    <!--<xsl:apply-templates/>-->
                </table>
            </body>
        </html>
    </xsl:template>
    <!--<xsl:template match="Valute">
        <tr><td><xsl:value-of select="CharCode"/></td>
            <td><xsl:value-of select="Name"/></td>
            <td><xsl:value-of select="Value"/></td></tr>
    </xsl:template>-->
</xsl:stylesheet>