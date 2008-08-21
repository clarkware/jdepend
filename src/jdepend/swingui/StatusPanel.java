package jdepend.swingui;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.BoxLayout;

/**
 * The <code>StatusPanel</code> class defines the status-related UI
 * components.
 * <p>
 * This panel primarily contains either a text field or a progress bar.
 * 
 * @author <b>Mike Clark</b>
 * @author Clarkware Consulting, Inc.
 */

public class StatusPanel extends JPanel {

    /**
     * Constructs a <code>StatusPanel</code>.
     */
    public StatusPanel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    /**
     * Sets the specified component as the current status component of this
     * panel.
     * 
     * @param component Status component.
     */
    public void setStatusComponent(JComponent component) {
        removeAll();
        add(component);
        repaint();
        validate();
    }
}