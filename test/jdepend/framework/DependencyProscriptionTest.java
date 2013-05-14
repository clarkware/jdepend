package jdepend.framework;

public class DependencyProscriptionTest extends JDependTestCase {

	private JDepend jdepend;

	public DependencyProscriptionTest(String name) {
		super(name);
	}

    protected void setUp() {
        super.setUp();
    }
    
    public void testPass() {
        DependencyDirective proscription = new DependencyProscription();

        JavaPackage expectedA = proscription.addPackage("A");
        JavaPackage expectedB = proscription.addPackage("B");

        expectedA.dependsUpon(expectedB);

        JavaPackage actualA = new JavaPackage("A");
        JavaPackage actualB = new JavaPackage("B");

        actualB.dependsUpon(actualA);

        jdepend.addPackage(actualA);
        jdepend.addPackage(actualB);

        assertEquals(true, jdepend.followsDirective(proscription));
    }
    
    public void testFail() {
        DependencyDirective proscription = new DependencyProscription();

        JavaPackage forbiddenA = proscription.addPackage("A");
        JavaPackage forbiddenB = proscription.addPackage("B");

        forbiddenA.dependsUpon(forbiddenB);

        JavaPackage actualA = new JavaPackage("A");
        JavaPackage actualB = new JavaPackage("B");

        actualA.dependsUpon(actualB);

        jdepend.addPackage(actualA);
        jdepend.addPackage(actualB);

        assertEquals(false, jdepend.followsDirective(proscription));
    }
}
