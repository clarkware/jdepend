package jdepend.framework;

import java.io.IOException;

/**
 * @author <b>Mike Clark</b>
 * @author Clarkware Consulting, Inc.
 */

public class ConstraintTest extends JDependTestCase {

    private JDepend jdepend;

    public ConstraintTest(String name) {
        super(name);
    }

    protected void setUp() {
        super.setUp();
        PackageFilter filter = new PackageFilter();
        filter.addPackage("java.*");
        filter.addPackage("javax.*");
        jdepend = new JDepend(filter);
    }

    public void testMatchPass() {

        DependencyConstraint constraint = new DependencyConstraint();

        JavaPackage expectedA = constraint.addPackage("A");
        JavaPackage expectedB = constraint.addPackage("B");

        expectedA.dependsUpon(expectedB);

        JavaPackage actualA = new JavaPackage("A");
        JavaPackage actualB = new JavaPackage("B");

        actualA.dependsUpon(actualB);

        jdepend.addPackage(actualA);
        jdepend.addPackage(actualB);

        assertEquals(true, jdepend.dependencyMatch(constraint));
    }

    public void testMatchFail() {

        DependencyConstraint constraint = new DependencyConstraint();

        JavaPackage expectedA = constraint.addPackage("A");
        JavaPackage expectedB = constraint.addPackage("B");
        JavaPackage expectedC = constraint.addPackage("C");

        expectedA.dependsUpon(expectedB);

        JavaPackage actualA = new JavaPackage("A");
        JavaPackage actualB = new JavaPackage("B");
        JavaPackage actualC = new JavaPackage("C");

        actualA.dependsUpon(actualB);
        actualA.dependsUpon(actualC);

        jdepend.addPackage(actualA);
        jdepend.addPackage(actualB);
        jdepend.addPackage(actualC);

        assertEquals(false, jdepend.dependencyMatch(constraint));
    }

    public void testJDependConstraints() throws IOException {
        
        jdepend.addDirectory(getBuildDir());

        jdepend.analyze();

        DependencyConstraint constraint = new DependencyConstraint();

        JavaPackage junitframework = constraint.addPackage("junit.framework");
        JavaPackage junitui = constraint.addPackage("junit.textui");
        JavaPackage framework = constraint.addPackage("jdepend.framework");
        JavaPackage text = constraint.addPackage("jdepend.textui");
        JavaPackage xml = constraint.addPackage("jdepend.xmlui");
        JavaPackage swing = constraint.addPackage("jdepend.swingui");

        framework.dependsUpon(junitframework);
        framework.dependsUpon(junitui);
        text.dependsUpon(framework);
        xml.dependsUpon(text);
        swing.dependsUpon(framework);

        assertEquals(true, jdepend.dependencyMatch(constraint));
    }
}