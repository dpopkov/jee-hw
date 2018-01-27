<xsl:stylesheet
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" encoding="UTF-8" omit-xml-declaration="yes"/>
    <xsl:template match="/ValCurs">
        <table border="1">
            <tr><th>Code</th><th>Name</th><th>Value</th></tr>
            <xsl:apply-templates select="Valute"/>
        </table>
    </xsl:template>
    <xsl:template match="/ValCurs/Valute[CharCode='AZN' or CharCode='GBP' or CharCode='KRW']">
        <tr><td><xsl:value-of select="CharCode"/></td>
            <td><xsl:value-of select="Name"/></td>
            <td><xsl:value-of select="Value"/></td></tr>
    </xsl:template>
    <xsl:template match="/ValCurs/Valute[CharCode!='AZN' and CharCode!='GBP' and CharCode!='KRW']"/>
</xsl:stylesheet>