<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="emailMessage">
        <html>
            <body>
                <xsl:apply-templates/>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="emailMessage/greeting">
        <h1>
            <xsl:apply-templates/>
        </h1>
    </xsl:template>

    <xsl:template match="scope">
        <h3>
            <xsl:apply-templates/>
        </h3>
    </xsl:template>

    <xsl:template match="scope/scopeMessage">
        <span>
            <xsl:apply-templates/>
        </span>
    </xsl:template>
    <xsl:template match="scope/scopePurpose">
        <span>
            <xsl:apply-templates/>
        </span>
    </xsl:template>
    <xsl:template match="message">
        <p>
            <xsl:apply-templates/>
        </p>
    </xsl:template>
    <xsl:template match="url">
        <span>
            <a href="{@href}">
                <xsl:apply-templates/>
            </a>
        </span>
    </xsl:template>
</xsl:stylesheet>