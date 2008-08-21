package jdepend.framework;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author <b>Mike Clark</b>
 * @author Clarkware Consulting, Inc.
 */

public class CycleTest extends JDependTestCase {

    public CycleTest(String name) {
        super(name);
    }

    public void testNoCycles() {

        JavaPackage a = new JavaPackage("A");
        JavaPackage b = new JavaPackage("B");

        a.dependsUpon(b);

        List aCycles = new ArrayList();
        assertEquals(false, a.containsCycle());
        assertEquals(false, a.collectCycle(aCycles));
        assertListEquals(aCycles, new String[] {});

        List bCycles = new ArrayList();
        assertEquals(false, b.containsCycle());
        assertEquals(false, b.collectCycle(bCycles));
        assertListEquals(bCycles, new String[] {});
    }

    public void test2Node1BranchCycle() {

        JavaPackage a = new JavaPackage("A");
        JavaPackage b = new JavaPackage("B");

        a.dependsUpon(b);
        b.dependsUpon(a);

        List aCycles = new ArrayList();
        assertEquals(true, a.containsCycle());
        assertEquals(true, a.collectCycle(aCycles));
        assertListEquals(aCycles, new String[] { "A", "B", "A"});

        List bCycles = new ArrayList();
        assertEquals(true, b.containsCycle());
        assertEquals(true, b.collectCycle(bCycles));
        assertListEquals(bCycles, new String[] { "B", "A", "B"});
    }

    public void test3Node1BranchCycle() {

        JavaPackage a = new JavaPackage("A");
        JavaPackage b = new JavaPackage("B");
        JavaPackage c = new JavaPackage("C");

        a.dependsUpon(b);
        b.dependsUpon(c);
        c.dependsUpon(a);

        List aCycles = new ArrayList();
        assertEquals(true, a.containsCycle());
        assertEquals(true, a.collectCycle(aCycles));
        assertListEquals(aCycles, new String[] { "A", "B", "C", "A"});

        List bCycles = new ArrayList();
        assertEquals(true, b.containsCycle());
        assertEquals(true, b.collectCycle(bCycles));
        assertListEquals(bCycles, new String[] { "B", "C", "A", "B"});

        List cCycles = new ArrayList();
        assertEquals(true, c.containsCycle());
        assertEquals(true, c.collectCycle(cCycles));
        assertListEquals(cCycles, new String[] { "C", "A", "B", "C"});
    }

    public void test3Node1BranchSubCycle() {

        JavaPackage a = new JavaPackage("A");
        JavaPackage b = new JavaPackage("B");
        JavaPackage c = new JavaPackage("C");

        a.dependsUpon(b);
        b.dependsUpon(c);
        c.dependsUpon(b);

        List aCycles = new ArrayList();
        assertEquals(true, a.containsCycle());
        assertEquals(true, a.collectCycle(aCycles));
        assertListEquals(aCycles, new String[] { "A", "B", "C", "B"});

        List bCycles = new ArrayList();
        assertEquals(true, b.containsCycle());
        assertEquals(true, b.collectCycle(bCycles));
        assertListEquals(bCycles, new String[] { "B", "C", "B"});

        List cCycles = new ArrayList();
        assertEquals(true, c.containsCycle());
        assertEquals(true, c.collectCycle(cCycles));
        assertListEquals(cCycles, new String[] { "C", "B", "C"});
    }

    public void test3Node2BranchCycle() {

        JavaPackage a = new JavaPackage("A");
        JavaPackage b = new JavaPackage("B");
        JavaPackage c = new JavaPackage("C");

        a.dependsUpon(b);
        b.dependsUpon(a);

        a.dependsUpon(c);
        c.dependsUpon(a);

        List aCycles = new ArrayList();
        assertEquals(true, a.containsCycle());
        assertEquals(true, a.collectCycle(aCycles));
        assertListEquals(aCycles, new String[] { "A", "B", "A"});

        List bCycles = new ArrayList();
        assertEquals(true, b.containsCycle());
        assertEquals(true, b.collectCycle(bCycles));
        assertListEquals(bCycles, new String[] { "B", "A", "B"});

        List cCycles = new ArrayList();
        assertEquals(true, c.containsCycle());
        assertEquals(true, c.collectCycle(cCycles));
        assertListEquals(cCycles, new String[] { "C", "A", "B", "A"});
    }

    public void test5Node2BranchCycle() {

        JavaPackage a = new JavaPackage("A");
        JavaPackage b = new JavaPackage("B");
        JavaPackage c = new JavaPackage("C");
        JavaPackage d = new JavaPackage("D");
        JavaPackage e = new JavaPackage("E");

        a.dependsUpon(b);
        b.dependsUpon(c);
        c.dependsUpon(a);

        a.dependsUpon(d);
        d.dependsUpon(e);
        e.dependsUpon(a);

        List aCycles = new ArrayList();
        assertEquals(true, a.containsCycle());
        assertEquals(true, a.collectCycle(aCycles));
        assertListEquals(aCycles, new String[] { "A", "B", "C", "A"});

        List bCycles = new ArrayList();
        assertEquals(true, b.containsCycle());
        assertEquals(true, b.collectCycle(bCycles));
        assertListEquals(bCycles, new String[] { "B", "C", "A", "B"});

        List cCycles = new ArrayList();
        assertEquals(true, c.containsCycle());
        assertEquals(true, c.collectCycle(cCycles));
        assertListEquals(cCycles, new String[] { "C", "A", "B", "C"});

        List dCycles = new ArrayList();
        assertEquals(true, d.containsCycle());
        assertEquals(true, d.collectCycle(dCycles));
        assertListEquals(dCycles, new String[] { "D", "E", "A", "B", "C", "A"});

        List eCycles = new ArrayList();
        assertEquals(true, e.containsCycle());
        assertEquals(true, e.collectCycle(eCycles));
        assertListEquals(eCycles, new String[] { "E", "A", "B", "C", "A"});
    }

    protected void assertListEquals(List list, String names[]) {

        assertEquals(names.length, list.size());

        for (int i = 0; i < names.length; i++) {
            assertEquals(names[i], ((JavaPackage) list.get(i)).getName());
        }
    }

    protected void printCycles(List list) {
        Iterator i = list.iterator();
        while (i.hasNext()) {
            JavaPackage p = (JavaPackage) i.next();
            if (i.hasNext()) {
                System.out.print(p.getName() + "->");
            } else {
                System.out.println(p.getName());
            }
        }
    }
}