<?xml version="1.0"?>

<!--
  
  Takes the XML output from JDepend and transforms it
  into the 'dot' language used by Graphviz
  (http://www.research.att.com/sw/tools/graphviz/)
  to generate a project dependency graph.

  The packages show up as rectangles with the package name
  and the number of classes.  Arrows point to other packages
  the package depends on.  The rectangle is colored blue, but
  it turns to darker shades of red the further the package is
  from the 'main line'.

  Contributed by David Bock.

-->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:output method="text"/>
<xsl:template match="JDepend">
<Root-Element>
digraph g {
        graph [
	    rankdir = "LR"
	];
	node [
	    fontsize = "12"
	    fontname = "Courier"
	    shape = "ellipse"
	];
	edge[];
	<xsl:apply-templates select="Packages"/>
}
</Root-Element>
</xsl:template>

<xsl:template match="Packages">
    <xsl:apply-templates select="Package" mode="node"/>
</xsl:template>

<xsl:template match="Package" mode="node">
    <xsl:text>"</xsl:text><xsl:value-of select="@name"/> <xsl:text>" [
        label="</xsl:text><xsl:value-of
	select="@name"/><xsl:text> | Total Classes: </xsl:text><xsl:value-of select="Stats/TotalClasses/."/>
	<xsl:text>"
	shape="record"
	color=".99 </xsl:text>
        <xsl:choose>
            <xsl:when test="Stats/D">
                <xsl:value-of select="Stats/D/."/>
            </xsl:when>
            <xsl:otherwise>
                <xsl:text>0.0</xsl:text>
            </xsl:otherwise>
        </xsl:choose>
        <xsl:text> .9"
	style=filled
    ];
    </xsl:text>
    <xsl:apply-templates select="DependsUpon"/>
</xsl:template>

<xsl:template match="Package" mode="edge">
    <xsl:text>"</xsl:text><xsl:value-of select="../../@name"/> <xsl:text>" -&gt; "</xsl:text><xsl:value-of select="."/><xsl:text>"
    </xsl:text>
</xsl:template>

<xsl:template match="DependsUpon">
    <xsl:apply-templates select="Package" mode="edge"/>
</xsl:template>

</xsl:stylesheet>
