package jdepend.swingui;

import java.util.*;

import jdepend.framework.*;

/**
 * The <code>AfferentNode</code> class is a <code>PackageNode</code> for an
 * afferent Java package and its afferent packages.
 * 
 * @author <b>Mike Clark</b>
 * @author Clarkware Consulting, Inc.
 */

public class AfferentNode extends PackageNode {

    /**
     * Constructs an <code>AfferentNode</code> with the specified parent node
     * and afferent Java package.
     * 
     * @param parent Parent package node.
     * @param jPackage Afferent Java package.
     */
    public AfferentNode(PackageNode parent, JavaPackage jPackage) {
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
        return new AfferentNode(parent, jPackage);
    }

    /**
     * Returns the collection of Java packages coupled to the package
     * represented in this node.
     * 
     * @return Collection of coupled packages.
     */
    protected Collection getCoupledPackages() {
        return getPackage().getAfferents();
    }

    /**
     * Returns the string representation of this node in it's current tree
     * context.
     * 
     * @return Node label.
     */
    public String toString() {
        if (getParent() == null) {
            return "Used By - Afferent Dependencies" + " ("
                    + getChildren().size() + " Packages)";
        }

        return super.toString();
    }
}

