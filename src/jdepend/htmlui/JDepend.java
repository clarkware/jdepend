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
 * @author <b>Richard Oliver Legendi</b> (<a href="http://people.inf.elte.hu/legendi">rlegendi</a>)
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
	
	/**
	 * Includes a file to the current <code>PrintWriter</code> object.
	 * 
	 * <p>
	 * File allocation is performed through the {@link Class#getResourceAsStream(String)} function, using the
	 * <code>'/'</code> prefix helps navigation with an absolute name of the referred resource.
	 * </p>
	 * 
	 * @param fileName the file to append to the current writer object
	 */
	private void includeFile(final String fileName) {
		BufferedReader br = null;
		
		try {
			br = new BufferedReader( new InputStreamReader( JDepend.class.getResourceAsStream( fileName ) ) );
			
			String line = null;
			while ( ( line = br.readLine() ) != null ) {
				getWriter().println( line );
			}
		} catch (final Exception e) {
			usage( e.getMessage() );
		} finally {
			if ( br != null ) {
				try {
					br.close();
				} catch (final IOException e) {
					usage( e.getMessage() );
				}
			}
		}
	}
	
	protected void printHeader() {
		getWriter().println( "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">" );
		getWriter().println( "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\">" );
		getWriter().println( "<head>" );
		getWriter().println( "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" );
		getWriter().println( "<meta name=\"generator\" content=\"JDepend\" />" );
		getWriter().println( "<title>JDepend Analysis Report</title>" );
		
		getWriter().println( "<style type=\"text/css\">" );
		includeFile( "/main.css" );
		getWriter().println( "</style>" );
		
		getWriter().println( "</head>" );
		getWriter().println( "<body>" );
		getWriter().println( "<h1>JDepend Analysis Report</h1>" );
		getWriter().println( "<p>Designed to use with <a href=\"http://clarkware.com/software/JDepend.html\">JDepend</a> and <a href=\"http://ant.apache.org/ant\">Ant</a>.</p>" );
		getWriter().println( "<p>Report generated at " + new Date() );
		
		getWriter().println( "<h2>General Information</h2>" );
		includeFile( "/info.html" );
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
		getWriter().println( tab() + "<p class=\"message\">No stats available: package referenced, but not analyzed.</p>" ); // CHECKME
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
		getWriter().println( tab() + "<p class=\"message\">Efferents not available.</p>" );
	}
	
	protected void printAfferentsHeader() {
		getWriter().println( "<h4>UsedBy</h4>" );
	}
	
	protected void printAfferentsFooter() {
		;
	}
	
	protected void printAfferentsError() {
		getWriter().println( tab() + "<p class=\"message\">Afferents not available.</p>" );
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
		getWriter().println( "<!-- Table design by R. Christie: http://www.smashingmagazine.com/2008/08/13/top-10-css-table-designs/ -->" );
		
		getWriter().println( "<h2>Summary</h2>" );
		getWriter().println( "<table id=\"hor-minimalist-b\" >" );
		getWriter().println( "<thead>" );
		getWriter().println( tab( 1 ) + "<tr>" );
		getWriter().println( tab( 2 ) + "<th scope=\"col\">Name</th>" );
		getWriter().println( tab( 2 ) + "<th scope=\"col\">Class Count</th>" );
		getWriter().println( tab( 2 ) + "<th scope=\"col\">Abstract Class Count</th>" );
		getWriter().println( tab( 2 ) + "<th scope=\"col\">Ca</th>" );
		getWriter().println( tab( 2 ) + "<th scope=\"col\">Ce</th>" );
		getWriter().println( tab( 2 ) + "<th scope=\"col\">A</th>" );
		getWriter().println( tab( 2 ) + "<th scope=\"col\">I</th>" );
		getWriter().println( tab( 2 ) + "<th scope=\"col\">D</th>" );
		getWriter().println( tab( 2 ) + "<th scope=\"col\">V</th>" );
		getWriter().println( tab( 1 ) + "</tr>" );
		getWriter().println( "</thead>" );
		
		final Iterator i = packages.iterator();
		while ( i.hasNext() ) {
			final JavaPackage jPackage = (JavaPackage) i.next();
			
			getWriter().println( "<tbody>" );
			getWriter().println( tab( 1 ) + "<tr>" );
			getWriter().println( tab( 2 ) + "<td>" + jPackage.getName() + "</td>" );
			getWriter().println( tab( 2 ) + "<td>" + jPackage.getClassCount() + "</td>" );
			getWriter().println( tab( 2 ) + "<td>" + jPackage.getAbstractClassCount() + "</td>" );
			getWriter().println( tab( 2 ) + "<td>" + jPackage.afferentCoupling() + "</td>" );
			getWriter().println( tab( 2 ) + "<td>" + jPackage.efferentCoupling() + "</td>" );
			getWriter().println( tab( 2 ) + "<td>" + toFormattedString( jPackage.abstractness() ) + "</td>" );
			getWriter().println( tab( 2 ) + "<td>" + toFormattedString( jPackage.instability() ) + "</td>" );
			getWriter().println( tab( 2 ) + "<td>" + toFormattedString( jPackage.distance() ) + "</td>" );
			getWriter().println( tab( 2 ) + "<td>" + jPackage.getVolatility() + "</td>" );
			getWriter().println( tab( 1 ) + "</tr>" );
			getWriter().println( "</tbody>" );
		}
		
		getWriter().println( "</table>" );
		
		getWriter().println();
		getWriter().println( "<p class=\"footer\">Generated by <a href=\"http://www.clarkware.com/software/JDepend.html\">JDepend</a>.<br/> "
				+ "HTML output formatting written by <a href=\"http://people.inf.elte.hu/legendi/\">Richard O. Legendi</a>.</p>" );
		getWriter().println();
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
