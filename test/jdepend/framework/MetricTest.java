package jdepend.framework;

import java.io.IOException;
import java.text.NumberFormat;

/**
 * @author <b>Mike Clark</b>
 * @author Clarkware Consulting, Inc.
 */

public class MetricTest extends JDependTestCase {

    private JDepend jdepend;
    private static NumberFormat formatter;

    static {
        formatter = NumberFormat.getInstance();
        formatter.setMaximumFractionDigits(2);
    }

    public MetricTest(String name) {
        super(name);
    }

    protected void setUp() {
        super.setUp();

        PackageFilter filter = new PackageFilter();
        filter.addPackage("java.*");
        filter.addPackage("javax.*");

        jdepend = new JDepend(filter);
        jdepend.analyzeInnerClasses(false);
    }

    protected void tearDown() {
        super.tearDown();
    }

    public void testAnalyzeClassFiles() throws IOException {
        jdepend.addDirectory(getBuildDir());
        assertAnalyzePackages();
    }

    private void assertAnalyzePackages() {

        assertEquals(39, jdepend.countClasses());

        PackageFilter filter = jdepend.getFilter();
        filter.addPackage("junit.*");

        jdepend.analyze();

        assertFrameworkPackage();
        assertTextUIPackage();
        assertSwingUIPackage();
        assertXmlUIPackage();
    }

    private void assertFrameworkPackage() {

        JavaPackage p = jdepend.getPackage("jdepend.framework");
        assertNotNull(p);

        assertEquals(25, p.getConcreteClassCount());
        assertEquals(4, p.getAbstractClassCount());
        assertEquals(3, p.afferentCoupling());
        assertEquals(0, p.efferentCoupling());
        assertEquals(format(0.14f), format(p.abstractness()));
        assertEquals(format(0.0f), format(p.instability()));
        assertEquals(format(0.86f), format(p.distance()));
        assertEquals(1, p.getVolatility());
    }

    private void assertTextUIPackage() {

        JavaPackage p = jdepend.getPackage("jdepend.textui");
        assertNotNull(p);

        assertEquals(1, p.getConcreteClassCount());
        assertEquals(0, p.getAbstractClassCount());
        assertEquals(1, p.efferentCoupling());
        assertEquals("0", format(p.abstractness()));
        assertEquals(1, p.afferentCoupling());
        assertEquals(format(0.5f), format(p.instability()));
        assertEquals(format(0.5f), format(p.distance()));
        assertEquals(1, p.getVolatility());
    }

    private void assertSwingUIPackage() {

        JavaPackage p = jdepend.getPackage("jdepend.swingui");
        assertNotNull(p);

        assertEquals(7, p.getConcreteClassCount());
        assertEquals(1, p.getAbstractClassCount());
        assertEquals(0, p.afferentCoupling());
        assertEquals(1, p.efferentCoupling());
        assertEquals(format(0.12f), format(p.abstractness()));
        assertEquals("1", format(p.instability()));
        assertEquals(format(0.12f), format(p.distance()));
        assertEquals(1, p.getVolatility());
    }
    
    private void assertXmlUIPackage() {

        JavaPackage p = jdepend.getPackage("jdepend.xmlui");
        assertNotNull(p);

        assertEquals(1, p.getConcreteClassCount());
        assertEquals(0, p.getAbstractClassCount());
        assertEquals(0, p.afferentCoupling());
        assertEquals(2, p.efferentCoupling());
        assertEquals(format(0.0f), format(p.abstractness()));
        assertEquals("1", format(p.instability()));
        assertEquals(format(0.0f), format(p.distance()));
        assertEquals(1, p.getVolatility());
    }

    public void testConfiguredVolatility() throws IOException {

        jdepend.addDirectory(getBuildDir());

        JavaPackage pkg = new JavaPackage("jdepend.swingui");
        pkg.setVolatility(0);

        jdepend.addPackage(pkg);

        jdepend.analyze();

        JavaPackage analyzedPkg = jdepend.getPackage(pkg.getName());
        assertEquals(0, analyzedPkg.getVolatility());
        assertEquals(format(0.0f), format(analyzedPkg.distance()));
        assertEquals(7, analyzedPkg.getConcreteClassCount());
    }

    private String format(float f) {
        return formatter.format(f);
    }
}