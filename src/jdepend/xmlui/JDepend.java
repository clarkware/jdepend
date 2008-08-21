package jdepend.xmlui;

import java.io.*;
import java.util.*;

import java.text.NumberFormat;

import jdepend.framework.JavaClass;
import jdepend.framework.JavaPackage;

/**
 * The <code>JDepend</code> class analyzes directories of Java class files,
 * generates metrics for each Java package, and reports the metrics in an XML
 * format.
 * 
 * @author <b>Mike Clark</b>
 * @author Clarkware Consulting, Inc.
 */

public class JDepend extends jdepend.textui.JDepend {

    /**
     * Constructs a <code>JDepend</code> instance using standard output.
     */
    public JDepend() {
        this(new PrintWriter(System.out));
    }

    /**
     * Constructs a <code>JDepend</code> instance with the specified writer.
     * 
     * @param writer Writer.
     */
    public JDepend(PrintWriter writer) {
        super(writer);

        formatter = NumberFormat.getInstance(Locale.ENGLISH);
        formatter.setMaximumFractionDigits(2);
    }

    protected void printHeader() {
        getWriter().println("<?xml version=\"1.0\"?>");
        getWriter().println("<JDepend>");
    }

    protected void printFooter() {
        getWriter().println("</JDepend>");
    }

    protected void printPackagesHeader() {
        getWriter().println(tab() + "<Packages>");
    }

    protected void printPackagesFooter() {
        getWriter().println(tab() + "</Packages>");
    }

    protected void printPackageHeader(JavaPackage jPackage) {
        printSectionBreak();
        getWriter().println(
                tab(2) + "<Package name=\"" + jPackage.getName() + "\">");
    }

    protected void printPackageFooter(JavaPackage jPackage) {
        getWriter().println(tab(2) + "</Package>");
    }

    protected void printNoStats() {
        getWriter().println(
                tab(3) + "<error>No stats available: "
                        + "package referenced, but not analyzed.</error>");
    }

    protected void printStatistics(JavaPackage jPackage) {
        getWriter().println(tab(3) + "<Stats>");
        getWriter().println(
                tab(4) + "<TotalClasses>" + jPackage.getClassCount()
                        + "</TotalClasses>");
        getWriter().println(
                tab(4) + "<ConcreteClasses>" + jPackage.getConcreteClassCount()
                        + "</ConcreteClasses>");
        getWriter().println(
                tab(4) + "<AbstractClasses>" + jPackage.getAbstractClassCount()
                        + "</AbstractClasses>");
        getWriter().println(
                tab(4) + "<Ca>" + jPackage.afferentCoupling() + "</Ca>");
        getWriter().println(
                tab(4) + "<Ce>" + jPackage.efferentCoupling() + "</Ce>");
        getWriter().println(
                tab(4) + "<A>" + toFormattedString(jPackage.abstractness())
                        + "</A>");
        getWriter().println(
                tab(4) + "<I>" + toFormattedString(jPackage.instability())
                        + "</I>");
        getWriter().println(
                tab(4) + "<D>" + toFormattedString(jPackage.distance())
                        + "</D>");
        getWriter().println(tab(4) + "<V>" + jPackage.getVolatility() + "</V>");
        getWriter().println(tab(3) + "</Stats>");
    }

    protected void printClassName(JavaClass jClass) {
        getWriter().println(
                tab(4) + "<Class sourceFile=\"" + jClass.getSourceFile()
                        + "\">");
        getWriter().println(tab(5) + jClass.getName());
        getWriter().println(tab(4) + "</Class>");
    }

    protected void printPackageName(JavaPackage jPackage) {
        getWriter().println(
                tab(4) + "<Package>" + jPackage.getName() + "</Package>");
    }

    protected void printAbstractClassesHeader() {
        getWriter().println(tab(3) + "<AbstractClasses>");
    }

    protected void printAbstractClassesFooter() {
        getWriter().println(tab(3) + "</AbstractClasses>");
    }

    protected void printConcreteClassesHeader() {
        getWriter().println(tab(3) + "<ConcreteClasses>");
    }

    protected void printConcreteClassesFooter() {
        getWriter().println(tab(3) + "</ConcreteClasses>");
    }

    protected void printEfferentsHeader() {
        getWriter().println(tab(3) + "<DependsUpon>");
    }

    protected void printEfferentsFooter() {
        getWriter().println(tab(3) + "</DependsUpon>");
    }

    protected void printEfferentsError() {
        // do nothing
    }

    protected void printAfferentsHeader() {
        getWriter().println(tab(3) + "<UsedBy>");
    }

    protected void printAfferentsFooter() {
        getWriter().println(tab(3) + "</UsedBy>");
    }

    protected void printAfferentsError() {
        // do nothing
    }

    protected void printCyclesHeader() {
        printSectionBreak();
        getWriter().println(tab() + "<Cycles>");
    }

    protected void printCyclesFooter() {
        getWriter().println(tab() + "</Cycles>");
    }

    protected void printCycleHeader(JavaPackage jPackage) {
        getWriter().println(
                tab(2) + "<Package Name=\"" + jPackage.getName() + "\">");
    }

    protected void printCycleFooter() {
        getWriter().println(tab(2) + "</Package>");
        printSectionBreak();
    }

    protected void printCycleTarget(JavaPackage jPackage) {
        printCycleContributor(jPackage);
    }

    protected void printCycleContributor(JavaPackage jPackage) {
        getWriter().println(
                tab(3) + "<Package>" + jPackage.getName() + "</Package>");
    }

    protected void printSummary(Collection packages) {
        // do nothing
    }

    /**
     * Main.
     */
    public static void main(String args[]) {
        new JDepend().instanceMain(args);
    }
}