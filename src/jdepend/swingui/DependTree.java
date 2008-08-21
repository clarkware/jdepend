package jdepend.swingui;

import java.awt.*;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;

import jdepend.framework.JavaPackage;

/**
 * The <code>DependTree</code> class defines the graphical tree for displaying
 * the packages and their hierarchical dependencies.
 * 
 * @author <b>Mike Clark</b>
 * @author Clarkware Consulting, Inc.
 */

public class DependTree extends JPanel implements TreeSelectionListener {

    private JTree tree;

    private DependTreeModel model;

    /**
     * Constructs a <code>DependTree</code> with an empty tree model.
     */
    public DependTree() {
        this(new DependTreeModel(new AfferentNode(null, new JavaPackage(""))));
    }

    /**
     * Constructs a <code>DependTree</code> with the specified tree model.
     * 
     * @param model Depend tree model.
     */
    public DependTree(DependTreeModel model) {

        setBorder(BorderFactory.createTitledBorder(model.getRoot().toString()));

        setModel(model);

        setLayout(new BorderLayout());

        JScrollPane pane = createScrollPane();

        add(pane, "Center");
    }

    /**
     * Sets the tree model.
     * 
     * @param model Tree model.
     */
    public void setModel(DependTreeModel model) {
        this.model = model;
        setBorder(BorderFactory.createTitledBorder(model.getRoot().toString()));
        getTree().setModel(this.model);

    }

    /**
     * Returns the tree model.
     * 
     * @return Tree model.
     */
    public DependTreeModel getModel() {
        return (DependTreeModel) getTree().getModel();
    }

    /**
     * Registers the specified listener with this tree.
     * 
     * @param l Tree selection listener.
     */
    public void addTreeSelectionListener(TreeSelectionListener l) {
        getTree().addTreeSelectionListener(l);
    }

    /**
     * Callback method triggered whenever the value of the tree selection
     * changes.
     * 
     * @param te Event that characterizes the change.
     */
    public void valueChanged(TreeSelectionEvent te) {

        TreePath path = te.getNewLeadSelectionPath();

        if (path != null) {
            Object o = path.getLastPathComponent();
        }
    }

    /**
     * Creates and returns a scroll pane.
     * 
     * @return Scroll pane.
     */
    private JScrollPane createScrollPane() {
        JScrollPane pane = new JScrollPane(getTree());
        return pane;
    }

    /**
     * Creates and returns a peered tree.
     * 
     * @return Tree.
     */
    private JTree createTree() {

        JTree tree = new JTree();
        tree.setShowsRootHandles(false);
        tree.setFont(new Font("Dialog", Font.PLAIN, 12));
        tree.addTreeSelectionListener(this);
        tree.setRootVisible(false);
        tree.setLargeModel(true);

        return tree;
    }

    /*
     * Returns the peered tree. @return A non-null tree.
     */
    private JTree getTree() {
        if (tree == null) {
            tree = createTree();
        }

        return tree;
    }
}

