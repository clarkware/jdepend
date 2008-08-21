package jdepend.swingui;

import java.util.*;
import javax.swing.tree.*;
import javax.swing.event.*;

/**
 * The <code>DependTreeModel</code> class defines the data model being
 * observed by a <code>DependTree</code> instance.
 * 
 * @author <b>Mike Clark</b>
 * @author Clarkware Consulting, Inc.
 */

public class DependTreeModel implements TreeModel {

    private PackageNode root;

    private Vector listeners;

    /**
     * Constructs a <code>DependTreeModel</code> with the specified root
     * package node.
     * 
     * @param root Root package node.
     */
    public DependTreeModel(PackageNode root) {
        this.root = root;
        listeners = new Vector();
    }

    /**
     * Returns the root of the tree.
     * 
     * @return The root of the tree, or <code>null</code> if the tree has no
     *         nodes.
     */
    public Object getRoot() {
        return root;
    }

    /**
     * Returns the child of the specified parent at the specified index in the
     * parent's child collection.
     * <p>
     * The specified parent must be a node previously obtained from this data
     * source.
     * 
     * @param parent A node in the tree, obtained from this data source.
     * @param index Index of child in the parent's child collection.
     * @return Child.
     */
    public Object getChild(Object parent, int index) {

        Object answer = null;
        ArrayList children;

        if (parent instanceof PackageNode) {
            children = ((PackageNode) parent).getChildren();

            if (children != null) {
                if (index < children.size()) {
                    answer = children.get(index);
                }
            }
        }

        return answer;
    }

    /**
     * Returns the number of children for the specified parent.
     * <p>
     * The specified parent must be a node previously obtained from this data
     * source.
     * 
     * @param parent A node in the tree, obtained from this data source.
     * @return The number of children of the specified parent, or 0 if the
     *         parent is a leaf node or if it has no children.
     */
    public int getChildCount(Object parent) {

        int answer = 0;
        ArrayList children;

        if (parent instanceof PackageNode) {
            children = ((PackageNode) parent).getChildren();

            if (children != null) {
                answer = children.size();
            }
        }

        return answer;
    }

    /**
     * Determines whether the specified tree node is a leaf node.
     * 
     * @param o A node in the tree, obtained from this data source.
     * @return <code>true</code> if the node is a leaf; <code>false</code>
     *         otherwise.
     */
    public boolean isLeaf(Object o) {

        boolean answer = true;

        if (o instanceof PackageNode) {
            PackageNode node = (PackageNode) o;
            return node.isLeaf();
        }

        return answer;
    }

    /**
     * Callback method triggered when the value for the item specified by
     * <i>path </i> has changed to <i>newValue </i>.
     * 
     * @param path Path to the node that has changed.
     * @param newValue The new value of the node.
     */
    public void valueForPathChanged(TreePath path, Object newValue) {
        // do nothing
    }

    /**
     * Returns the index of the specified child within the specified parent.
     * 
     * @param parent Parent node.
     * @param child Child node.
     * @return Index of child within parent.
     */
    public int getIndexOfChild(Object parent, Object child) {
        int answer = -1;
        ArrayList children = null;

        if (parent instanceof PackageNode) {
            children = ((PackageNode) parent).getChildren();

            if (children != null) {
                answer = children.indexOf(child);
            }
        }

        return answer;
    }

    /**
     * Adds a listener for the <code>TreeModelEvent</code> posted after the
     * tree changes.
     * 
     * @param l The listener to add.
     */
    public void addTreeModelListener(TreeModelListener l) {

        if ((l != null) && !listeners.contains(l)) {
            listeners.addElement(l);
        }
    }

    /**
     * Removes a listener for <code>TreeModelEvent</code>s.
     * 
     * @param l The listener to remove.
     */
    public void removeTreeModelListener(TreeModelListener l) {
        listeners.removeElement(l);
    }
}

