package jdepend.framework;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author <b>Mike Clark</b>
 * @author Clarkware Consulting, Inc.
 */

public class AllTests {

    public static Test suite() {

        TestSuite suite = new TestSuite("JDepend Tests");

        suite.addTestSuite(ClassFileParserTest.class);
        suite.addTestSuite(ComponentTest.class);
        suite.addTestSuite(JarFileParserTest.class);
        suite.addTestSuite(ConstraintTest.class);
        suite.addTestSuite(CycleTest.class);
        suite.addTestSuite(CollectAllCyclesTest.class);
        suite.addTestSuite(FileManagerTest.class);
        suite.addTestSuite(FilterTest.class);
        suite.addTestSuite(MetricTest.class);
        suite.addTestSuite(PropertyConfiguratorTest.class);
        suite.addTestSuite(ExampleTest.class);

        return suite;
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}