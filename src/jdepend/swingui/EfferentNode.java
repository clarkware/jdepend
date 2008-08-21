package jdepend.swingui;

import java.util.*;

import jdepend.framework.*;

/**
 * The <code>EfferentNode</code> class is a <code>PackageNode</code> for an
 * efferent Java package and its efferent packages.
 * 
 * @author <b>Mike Clark</b>
 * @author Clarkware Consulting, Inc.
 */

public class EfferentNode extends PackageNode {

    /**
     * Constructs an <code>EfferentNode</code> with the specified parent node
     * and efferent Java package.
     * 
     * @param parent Parent package node.
     * @param jPackage Efferent Java package.
     */
    public EfferentNode(PackageNode parent, JavaPackage jPackage) {
        super(parent, jPackage);
    }

    /**
     * Creates and returns a <code>PackageNode</code> with the specified
     * parent node and Java package.
     * 
     * @param parent Parent package node.
     * @param jPackage Java package.
     * @return A non-null <code>PackageNode</code.
     */
    protected PackageNode makeNode(PackageNode parent, JavaPackage jPackage) {
        return new EfferentNode(parent, jPackage);
    }

    /**
     * Returns the collection of Java packages coupled to the package
     * represented in this node.
     * 
     * @return Collection of coupled packages.
     */
    protected Collection getCoupledPackages() {
        return getPackage().getEfferents();
    }

    /**
     * Indicates whether the specified package should be displayed as a child of
     * this node.
     * <p>
     * Efferent packages without classes are never shown at the root level to
     * exclude non-analyzed packages.
     * 
     * @param jPackage Package to test.
     * @return <code>true</code> to display the package; <code>false</code>
     *         otherwise.
     */
    public boolean isChild(JavaPackage jPackage) {
        if (getParent() != null) {
            return true;
        } else if (jPackage.getClassCount() > 0) {
            return true;
        }

        return false;
    }

    /**
     * Returns the string representation of this node in it's current tree
     * context.
     * 
     * @return Node label.
     */
    public String toString() {
        if (getParent() == null) {
            return "Depends Upon - Efferent Dependencies" + " ("
                    + getChildren().size() + " Packages)";
        }

        return super.toString();
    }
}

