package jdepend.framework;

import java.io.*;

import junit.framework.*;

/**
 * @author <b>Mike Clark</b>
 * @author Clarkware Consulting, Inc.
 */

public class JDependTestCase extends TestCase {

    private String homeDir;
    private String testDir;
    private String testDataDir;
    private String buildDir;
    private String packageSubDir;
    private String originalUserHome;

    
    public JDependTestCase(String name) {
        super(name);
    }

    protected void setUp() {

        homeDir = System.getProperty("jdepend.home");
        if (homeDir == null) {
            fail("Property 'jdepend.home' not defined");
        }
        homeDir = homeDir + File.separator;
        testDir = homeDir + File.separator + "test" + File.separator;
        testDataDir = testDir + "data" + File.separator;
        buildDir = homeDir + "build" + File.separator;
        packageSubDir = "jdepend" + File.separator + 
                        "framework" + File.separator;
        originalUserHome = System.getProperty("user.home");
    }

    protected void tearDown() {
        System.setProperty("user.home", originalUserHome);
    }

    public String getHomeDir() {
        return homeDir;
    }

    public String getTestDataDir() {
        return testDataDir;
    }
    
    public String getTestDir() {
        return testDir;
    }

    public String getBuildDir() {
        return buildDir;
    }
    
    public String getPackageSubDir() {
        return packageSubDir;
    }
}