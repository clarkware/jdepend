package jdepend.framework;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 * @author <b>Mike Clark</b>
 * @author Clarkware Consulting, Inc.
 */

public class JarFileParserTest extends JDependTestCase {

    private File jarFile;
    private File zipFile;

    public JarFileParserTest(String name) {
        super(name);
    }

    protected void setUp() {
        super.setUp();

        jarFile = new File(getTestDataDir() + "test.jar");
        zipFile = new File(getTestDataDir() + "test.zip");
    }

    protected void tearDown() {
        super.tearDown();
    }

    public void testInvalidJarFile() throws IOException {

        JavaClassBuilder builder = new JavaClassBuilder();
        File bogusFile = new File(getTestDataDir() + "bogus.jar");

        try {

            builder.buildClasses(bogusFile);
            fail("Should raise IOException");

        } catch (IOException expected) {
            assertTrue(true);
        }
    }

    public void testInvalidZipFile() throws IOException {

        JavaClassBuilder builder = new JavaClassBuilder();
        File bogusFile = new File(getTestDataDir() + "bogus.zip");

        try {

            builder.buildClasses(bogusFile);
            fail("Should raise IOException");

        } catch (IOException expected) {
            assertTrue(true);
        }
    }

    public void testJarFile() throws IOException {

        JavaClassBuilder builder = new JavaClassBuilder();

        Collection classes = builder.buildClasses(jarFile);
        assertEquals(5, classes.size());

        assertClassesExist(classes);
        assertInnerClassesExist(classes);
    }

    public void testJarFileWithoutInnerClasses() throws IOException {

        FileManager fm = new FileManager();
        fm.acceptInnerClasses(false);

        JavaClassBuilder builder = new JavaClassBuilder(fm);

        Collection classes = builder.buildClasses(jarFile);
        assertEquals(4, classes.size());

        assertClassesExist(classes);
    }

    public void testZipFile() throws IOException {

        JavaClassBuilder builder = new JavaClassBuilder();

        Collection classes = builder.buildClasses(zipFile);
        assertEquals(5, classes.size());

        assertClassesExist(classes);
        assertInnerClassesExist(classes);
    }

    public void testZipFileWithoutInnerClasses() throws IOException {

        FileManager fm = new FileManager();
        fm.acceptInnerClasses(false);

        JavaClassBuilder builder = new JavaClassBuilder(fm);

        Collection classes = builder.buildClasses(zipFile);
        assertEquals(4, classes.size());

        assertClassesExist(classes);
    }
    
    public void testCountClasses() throws IOException {

        JDepend jdepend = new JDepend();
        jdepend.addDirectory(getTestDataDir());

        jdepend.analyzeInnerClasses(true);
        assertEquals(10, jdepend.countClasses());

        jdepend.analyzeInnerClasses(false);
        assertEquals(8, jdepend.countClasses());
    }

    private void assertClassesExist(Collection classes) {
        assertTrue(classes.contains(new JavaClass(
                "jdepend.framework.ExampleAbstractClass")));
        assertTrue(classes.contains(new JavaClass(
                "jdepend.framework.ExampleInterface")));
        assertTrue(classes.contains(new JavaClass(
                "jdepend.framework.ExampleConcreteClass")));
    }
    
    private void assertInnerClassesExist(Collection classes) {
        assertTrue(classes.contains(new JavaClass(
                "jdepend.framework.ExampleConcreteClass$ExampleInnerClass")));
    }
}