package jdepend.htmlui;

import java.io.*;
import java.util.*;

import java.text.NumberFormat;

import jdepend.framework.JavaClass;
import jdepend.framework.JavaPackage;

/**
 * The <code>JDepend</code> class analyzes directories of Java class files, generates metrics for each Java package, and
 * reports the metrics in a HTML format.
 * 
 * @author <b>Richard Oliver Legendi</b> (rlegendi)
 */
public class JDepend
		extends jdepend.textui.JDepend {
	
	/**
	 * Constructs a <code>JDepend</code> instance using standard output.
	 */
	public JDepend() {
		this( new PrintWriter( System.out ) );
	}
	
	/**
	 * Constructs a <code>JDepend</code> instance with the specified writer.
	 * 
	 * @param writer Writer.
	 */
	public JDepend(final PrintWriter writer) {
		super( writer );
		
		formatter = NumberFormat.getInstance( Locale.ENGLISH );
		formatter.setMaximumFractionDigits( 2 );
	}
	
	// TODO This definitely needs some update. If there're more source folders (e.g. src, test), it may
	// be impossible to locate them correctly.
	private String findJavaClassLocation(final JavaClass javaClass) {
		return "src" + File.separatorChar + javaClass.getName().replace( '.', File.separatorChar );
	}
	
	protected void printHeader() {
		// TODO Some prettier CSS would be helpful
		getWriter().println( "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">" );
		getWriter().println( "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\">" );
		getWriter().println( "<head>" );
		getWriter().println( "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" );
		getWriter().println( "<meta name=\"generator\" content=\"JDepend\" />" );
		getWriter().println( "<title>JDepend Analysis Report</title>" );
		getWriter().println( "<style type=\"text/css\">" );
		getWriter().println( tab() + "body {" );
		getWriter().println( tab( 2 ) + "color:#000000;" );
		getWriter().println( tab() + "}" );
		getWriter().println( "</style>" );
		getWriter().println( "</head>" );
		getWriter().println( "<body>" );
		getWriter().println( "<h1>JDepend Analysis Report</h1>" );
		getWriter().println( "<p>Designed to use <a href=\"http://clarkware.com/software/JDepend.html\">JDepend</a> with <a href=\"http://ant.apache.org/ant\">Ant</a>.</p>" );
	}
	
	protected void printFooter() {
		getWriter().println( "</body>" );
		getWriter().println( "</html>" );
	}
	
	protected void printPackagesHeader() {
		getWriter().println( "<h2>Packages</h2>" );
	}
	
	protected void printPackagesFooter() {
		;
	}
	
	protected void printPackageHeader(final JavaPackage jPackage) {
		printSectionBreak();
		getWriter().println( "<h3>Package " + jPackage.getName() + "</h3>" );
	}
	
	protected void printPackageFooter(final JavaPackage jPackage) {
		getWriter().println( "<hr/>" );
	}
	
	protected void printNoStats() {
		getWriter().println( tab() + "<p id=\"error\">No stats available: package referenced, but not analyzed.</p>" ); // CHECKME
	}
	
	protected void printStatistics(final JavaPackage jPackage) {
		getWriter().println( tab( 1 ) + "<h4>Statistics</h4>" );
		getWriter().println( tab( 1 ) + "<ul>" );
		getWriter().println( tab( 2 ) + "<li>Total Number of Classes: " + jPackage.getClassCount() + "</li>" );
		getWriter().println( tab( 2 ) + "<li>Concrete Classes:" + jPackage.getConcreteClassCount() + "</li>" );
		getWriter().println( tab( 2 ) + "<li>Abstract Classes:" + jPackage.getAbstractClassCount() + "</li>" );
		getWriter().println( tab( 2 ) + "<li>Ca:" + jPackage.afferentCoupling() + "</li>" );
		getWriter().println( tab( 2 ) + "<li>Ce:" + jPackage.efferentCoupling() + "</li>" );
		getWriter().println( tab( 2 ) + "<li>A:" + toFormattedString( jPackage.abstractness() ) + "</li>" );
		getWriter().println( tab( 2 ) + "<li>I:" + toFormattedString( jPackage.instability() ) + "</li>" );
		getWriter().println( tab( 2 ) + "<li>D:" + toFormattedString( jPackage.distance() ) + "</li>" );
		getWriter().println( tab( 2 ) + "<li>V:" + jPackage.getVolatility() + "</li>" );
		getWriter().println( tab( 1 ) + "</ul>" );
	}
	
	protected void printClassName(final JavaClass jClass) {
		getWriter().println( "Class <a href=\"" + findJavaClassLocation( jClass ) + "\">" + jClass.getName() + "</a><br/>" );
	}
	
	protected void printPackageName(final JavaPackage jPackage) {
		getWriter().println( "Package " + jPackage.getName() + "<br/>" );
	}
	
	protected void printAbstractClassesHeader() {
		getWriter().println( "<h4>AbstractClasses</h4>" );
	}
	
	protected void printAbstractClassesFooter() {
		;
	}
	
	protected void printConcreteClassesHeader() {
		getWriter().println( "<h4>ConcreteClasses</h4>" );
	}
	
	protected void printConcreteClassesFooter() {
		;
	}
	
	protected void printEfferentsHeader() {
		getWriter().println( "<h4>DependsUpon</h4>" );
	}
	
	protected void printEfferentsFooter() {
		;
	}
	
	protected void printEfferentsError() {
		getWriter().println( tab() + "<p id=\"error\">Efferents not available.</p>" );
	}
	
	protected void printAfferentsHeader() {
		getWriter().println( "<h4>UsedBy</h4>" );
	}
	
	protected void printAfferentsFooter() {
		;
	}
	
	protected void printAfferentsError() {
		getWriter().println( tab() + "<p id=\"error\">Afferents not available.</p>" );
	}
	
	protected void printCyclesHeader() {
		printSectionBreak();
		getWriter().println( tab() + "<h2>Package Dependency Cycles</h2>" );
	}
	
	protected void printCyclesFooter() {
		getWriter().println( "<hr/>" );
	}
	
	protected void printCycleHeader(final JavaPackage jPackage) {
		getWriter().println( "<h3>Package " + jPackage.getName() + "</h3>" );
		getWriter().println( "<ul>" );
	}
	
	protected void printCycleFooter() {
		getWriter().println( "</ul>" );
		printSectionBreak();
	}
	
	protected void printCycleTarget(final JavaPackage jPackage) {
		printCycleContributor( jPackage );
	}
	
	protected void printCycleContributor(final JavaPackage jPackage) {
		getWriter().println( "<li>" + jPackage.getName() + "</li>" );
	}
	
	@SuppressWarnings("rawtypes")
	protected void printSummary(final Collection packages) {
		getWriter().println( "<h2>Summary</h2>" );
		getWriter().println( "<table border=\"1\">" );
		getWriter().println( "<tr>" );
		getWriter().println( "<td>Name</td> <td>Class Count</td> <td>Abstract Class Count</td> <td>Ca</td> <td>Ce</td> <td>A</td> <td>I</td> <td>D</td> <td>V</td>" );
		getWriter().println( "</tr>" );
		
		final Iterator i = packages.iterator();
		while ( i.hasNext() ) {
			final JavaPackage jPackage = (JavaPackage) i.next();
			
			getWriter().println( "<tr>" );
			getWriter().print( "<td>" + jPackage.getName() + "</td>" );
			getWriter().print( "<td>" + jPackage.getClassCount() + "</td>" );
			getWriter().print( "<td>" + jPackage.getAbstractClassCount() + "</td>" );
			getWriter().print( "<td>" + jPackage.afferentCoupling() + "</td>" );
			getWriter().print( "<td>" + jPackage.efferentCoupling() + "</td>" );
			getWriter().print( "<td>" + toFormattedString( jPackage.abstractness() ) + "</td>" );
			getWriter().print( "<td>" + toFormattedString( jPackage.instability() ) + "</td>" );
			getWriter().print( "<td>" + toFormattedString( jPackage.distance() ) + "</td>" );
			getWriter().println( "<td>" + jPackage.getVolatility() + "</td>" );
			getWriter().println( "</tr>" );
		}
		
		getWriter().println( "</table>" );
	}
	
	/**
	 * Main function, which is a delegate itself to {@link JDepend#instanceMain(String[])}.
	 * 
	 * @param args passed directly to the <code>instanceMain()</code> method
	 */
	public static void main(final String args[]) {
		new JDepend().instanceMain( args );
	}
	
}
