#!/bin/sh

COMMAND=$0
JDEPEND_DIR=`dirname $COMMAND`/..

GRAPHVIZ_PATH=~/Applications/Graphviz/Graphviz.app/Contents/MacOS

java -classpath $JDEPEND_DIR/lib/jdepend.jar jdepend.xmlui.JDepend -file jdepend.xml $JDEPEND_DIR/lib/jdepend.jar

xsltproc jdepend2dot.xsl jdepend.xml > jdepend.dot

$GRAPHVIZ_PATH/dot -Tpng jdepend.dot -o jdepend-report.png
#$GRAPHVIZ_PATH/dot -Tsvg jdepend.dot -o jdepend-report.svg
