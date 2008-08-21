package jdepend.swingui;

import java.text.NumberFormat;
import java.util.*;

import jdepend.framework.*;

/**
 * The <code>PackageNode</code> class defines the default behavior for tree
 * nodes representing Java packages.
 * 
 * @author <b>Mike Clark</b>
 * @author Clarkware Consulting, Inc.
 */

public abstract class PackageNode {

    private PackageNode parent;

    private JavaPackage jPackage;

    private ArrayList children;

    private static NumberFormat formatter;
    static {
        formatter = NumberFormat.getInstance();
        formatter.setMaximumFractionDigits(2);
    }

    /**
     * Constructs a <code>PackageNode</code> with the specified package and
     * its collection of dependent packages.
     * 
     * @param parent Parent package node.
     * @param jPackage Java package.
     */
    public PackageNode(PackageNode parent, JavaPackage jPackage) {
        this.parent = parent;
        this.jPackage = jPackage;
        children = null;
    }

    /**
     * Returns the Java package represented in this node.
     * 
     * @return Java package.
     */
    public JavaPackage getPackage() {
        return jPackage;
    }

    /**
     * Returns the parent of this package node.
     * 
     * @return Parent package node.
     */
    public PackageNode getParent() {
        return parent;
    }

    /**
     * Indicates whether this node is a leaf node.
     * 
     * @return <code>true</code> if this node is a leaf; <code>false</code>
     *         otherwise.
     */
    public boolean isLeaf() {
        if (getCoupledPackages().size() > 0) {
            return false;
        }

        return true;
    }

    /**
     * Creates and returns a <code>PackageNode</code> with the specified
     * parent node and Java package.
     * 
     * @param parent Parent package node.
     * @param jPackage Java package.
     * @return A non-null <code>PackageNode</code.
     */
    protected abstract PackageNode makeNode(PackageNode parent,
            JavaPackage jPackage);

    /**
     * Returns the collection of Java packages coupled to the package
     * represented in this node.
     * 
     * @return Collection of coupled packages.
     */
    protected abstract Collection getCoupledPackages();

    /**
     * Indicates whether the specified package should be displayed as a child of
     * this node.
     * 
     * @param jPackage Package to test.
     * @return <code>true</code> to display the package; <code>false</code>
     *         otherwise.
     */
    public boolean isChild(JavaPackage jPackage) {
        return true;
    }

    /**
     * Returns the child package nodes of this node.
     * 
     * @return Collection of child package nodes.
     */
    public ArrayList getChildren() {

        if (children == null) {

            children = new ArrayList();
            ArrayList packages = new ArrayList(getCoupledPackages());
            Collections.sort(packages, new PackageComparator(PackageComparator
                    .byName()));
            Iterator i = packages.iterator();
            while (i.hasNext()) {
                JavaPackage jPackage = (JavaPackage) i.next();
                if (isChild(jPackage)) {
                    PackageNode childNode = makeNode(this, jPackage);
                    children.add(childNode);
                }
            }
        }

        return children;
    }

    /**
     * Returns the string representation of this node's metrics.
     * 
     * @return Metrics string.
     */
    public String toMetricsString() {
        StringBuffer label = new StringBuffer();
        label.append(getPackage().getName());
        label.append("  (");
        label.append("CC: " + getPackage().getConcreteClassCount() + "  ");
        label.append("AC: " + getPackage().getAbstractClassCount() + "  ");
        label.append("Ca: " + getPackage().afferentCoupling() + "  ");
        label.append("Ce: " + getPackage().efferentCoupling() + "  ");
        label.append("A: " + format(getPackage().abstractness()) + "  ");
        label.append("I: " + format(getPackage().instability()) + "  ");
        label.append("D: " + format(getPackage().distance()) + "  ");
        label.append("V: " + getPackage().getVolatility());
        if (getPackage().containsCycle()) {
            label.append(" Cyclic");
        }

        label.append(")");

        return label.toString();
    }

    /**
     * Returns the string representation of this node in it's current tree
     * context.
     * 
     * @return Node label.
     */
    public String toString() {

        if (getParent().getParent() == null) {
            return toMetricsString();
        }

        return getPackage().getName();
    }

    /*
     * Returns the specified number in a displayable format. @param number
     * Number to format. @return Formatted number.
     */
    private static String format(float f) {
        return formatter.format(f);
    }
}

