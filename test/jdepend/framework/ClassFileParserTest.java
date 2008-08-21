package jdepend.framework;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author <b>Mike Clark</b>
 * @author Clarkware Consulting, Inc.
 */

public class ClassFileParserTest extends JDependTestCase {

    private ClassFileParser parser;

    public ClassFileParserTest(String name) {
        super(name);
    }

    protected void setUp() {
        super.setUp();
        PackageFilter filter = new PackageFilter(new ArrayList());
        parser = new ClassFileParser(filter);
    }

    protected void tearDown() {
        super.tearDown();
    }

    public void testInvalidClassFile() {

        File f = new File(getTestDir() + getPackageSubDir() + 
                          "ExampleTest.java");

        try {

            parser.parse(f);
            fail("Invalid class file: Should raise IOException");

        } catch (IOException expected) {
            assertTrue(true);
        }
    }

    public void testInterfaceClass() throws IOException {

        File f = new File(getBuildDir() + getPackageSubDir() +
                          "ExampleInterface.class");
        
        JavaClass clazz = parser.parse(f);

        assertTrue(clazz.isAbstract());

        assertEquals("jdepend.framework.ExampleInterface", clazz.getName());

        assertEquals("ExampleInterface.java", clazz.getSourceFile());

        Collection imports = clazz.getImportedPackages();
        assertEquals(6, imports.size());

        assertTrue(imports.contains(new JavaPackage("java.math")));
        assertTrue(imports.contains(new JavaPackage("java.text")));
        assertTrue(imports.contains(new JavaPackage("java.lang")));
        assertTrue(imports.contains(new JavaPackage("java.io")));
        assertTrue(imports.contains(new JavaPackage("java.rmi")));
        assertTrue(imports.contains(new JavaPackage("java.util")));
    }

    public void testAbstractClass() throws IOException {

        File f = new File(getBuildDir() + getPackageSubDir() +
                          "ExampleAbstractClass.class");
        
        JavaClass clazz = parser.parse(f);

        assertTrue(clazz.isAbstract());

        assertEquals("jdepend.framework.ExampleAbstractClass", clazz.getName());

        assertEquals("ExampleAbstractClass.java", clazz.getSourceFile());

        Collection imports = clazz.getImportedPackages();
        assertEquals(7, imports.size());

        assertTrue(imports.contains(new JavaPackage("java.math")));
        assertTrue(imports.contains(new JavaPackage("java.text")));
        assertTrue(imports.contains(new JavaPackage("java.lang")));
        assertTrue(imports.contains(new JavaPackage("java.lang.reflect")));
        assertTrue(imports.contains(new JavaPackage("java.io")));
        assertTrue(imports.contains(new JavaPackage("java.rmi")));
        assertTrue(imports.contains(new JavaPackage("java.util")));
    }

    public void testConcreteClass() throws IOException {

        File f = new File(getBuildDir() + getPackageSubDir() +
                          "ExampleConcreteClass.class");
        
        JavaClass clazz = parser.parse(f);

        assertFalse(clazz.isAbstract());

        assertEquals("jdepend.framework.ExampleConcreteClass", clazz.getName());

        assertEquals("ExampleConcreteClass.java", clazz.getSourceFile());

        Collection imports = clazz.getImportedPackages();
        assertEquals(9, imports.size());

        assertTrue(imports.contains(new JavaPackage("java.net")));
        assertTrue(imports.contains(new JavaPackage("java.text")));
        assertTrue(imports.contains(new JavaPackage("java.sql")));
        assertTrue(imports.contains(new JavaPackage("java.lang")));
        assertTrue(imports.contains(new JavaPackage("java.io")));
        assertTrue(imports.contains(new JavaPackage("java.rmi")));
        assertTrue(imports.contains(new JavaPackage("java.util")));
        assertTrue(imports.contains(new JavaPackage("java.util.jar")));
        assertTrue(imports.contains(new JavaPackage("java.math")));
    }

    public void testInnerClass() throws IOException {

        File f = new File(getBuildDir() + getPackageSubDir() +
                          "ExampleConcreteClass$ExampleInnerClass.class");
        
        JavaClass clazz = parser.parse(f);

        assertFalse(clazz.isAbstract());

        assertEquals("jdepend.framework.ExampleConcreteClass$ExampleInnerClass",
                clazz.getName());

        assertEquals("ExampleConcreteClass.java", clazz.getSourceFile());

        Collection imports = clazz.getImportedPackages();
        assertEquals(1, imports.size());

        assertTrue(imports.contains(new JavaPackage("java.lang")));

    }

    public void testPackageClass() throws IOException {

        File f = new File(getBuildDir() + getPackageSubDir() +
                          "ExamplePackageClass.class");
        
        JavaClass clazz = parser.parse(f);

        assertFalse(clazz.isAbstract());

        assertEquals("jdepend.framework.ExamplePackageClass", clazz.getName());

        assertEquals("ExampleConcreteClass.java", clazz.getSourceFile());

        Collection imports = clazz.getImportedPackages();
        assertEquals(1, imports.size());

        assertTrue(imports.contains(new JavaPackage("java.lang")));

    }
}