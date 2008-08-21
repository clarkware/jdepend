package jdepend.framework;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 * @author <b>Mike Clark</b>
 * @author Clarkware Consulting, Inc.
 */

public class PropertyConfiguratorTest extends JDependTestCase {

    public PropertyConfiguratorTest(String name) {
        super(name);
    }

    protected void setUp() {
        super.setUp();

        System.setProperty("user.home", getTestDataDir());
    }

    protected void tearDown() {
        super.tearDown();
    }

    public void testDefaultFilters() {
        PropertyConfigurator c = new PropertyConfigurator();
        assertFiltersExist(c.getFilteredPackages());
        assertFalse(c.getAnalyzeInnerClasses());
    }

    public void testFiltersFromFile() throws IOException {

        String file = getTestDataDir() + "jdepend.properties";

        PropertyConfigurator c = new PropertyConfigurator(new File(file));

        assertFiltersExist(c.getFilteredPackages());
        assertFalse(c.getAnalyzeInnerClasses());
    }

    private void assertFiltersExist(Collection filters) {
        assertEquals(5, filters.size());
        assertTrue(filters.contains("java.*"));
        assertTrue(filters.contains("javax.*"));
        assertTrue(filters.contains("sun.*"));
        assertTrue(filters.contains("com.sun.*"));
        assertTrue(filters.contains("com.xyz.tests.*"));
    }

    public void testDefaultPackages() throws IOException {
        JDepend j = new JDepend();

        JavaPackage pkg = j.getPackage("com.xyz.a.neverchanges");
        assertNotNull(pkg);
        assertEquals(0, pkg.getVolatility());

        pkg = j.getPackage("com.xyz.b.neverchanges");
        assertNotNull(pkg);
        assertEquals(0, pkg.getVolatility());

        pkg = j.getPackage("com.xyz.c.neverchanges");
        assertNull(pkg);
    }
}