package jdepend.swingui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * The <code>AboutDialog</code> displays the about information.
 * 
 * @author <b>Mike Clark</b>
 * @author Clarkware Consulting, Inc.
 */

class AboutDialog extends JDialog {

    /**
     * Constructs an <code>AboutDialog</code> with the specified parent frame.
     * 
     * @param parent Parent frame.
     */
    public AboutDialog(JFrame parent) {
        super(parent);

        setTitle("About");

        setResizable(false);

        getContentPane().setLayout(new BorderLayout());
        setSize(300, 200);

        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        JLabel titleLabel = new JLabel("JDepend");
        titleLabel.setFont(new Font("dialog", Font.BOLD, 18));

        JLabel nameLabel = new JLabel("Mike Clark");
        nameLabel.setFont(new Font("dialog", Font.PLAIN, 12));

        JLabel companyLabel = new JLabel("Clarkware Consulting, Inc.");
        companyLabel.setFont(new Font("dialog", Font.PLAIN, 12));

        JLabel httpLabel = new JLabel("www.clarkware.com");
        httpLabel.setFont(new Font("dialog", Font.PLAIN, 12));

        JLabel blankLabel = new JLabel(" ");

        JButton closeButton = createButton("Close");

        panel.add(titleLabel, createConstraints(1, 1));

        panel.add(new JLabel(" "), createConstraints(1, 2));

        panel.add(nameLabel, createConstraints(1, 3));

        panel.add(companyLabel, createConstraints(1, 4));

        panel.add(httpLabel, createConstraints(1, 5));

        panel.add(new JLabel(" "), createConstraints(1, 6));
        panel.add(new JLabel(" "), createConstraints(1, 7));

        panel.add(closeButton, createConstraints(1, 9));

        getContentPane().add("Center", panel);

    }

    /**
     * Creates and returns a button with the specified label.
     * 
     * @param label Button label.
     * @return Button.
     */
    private JButton createButton(String label) {

        JButton button = new JButton(label);
        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        return button;
    }

    /**
     * Creates and returns a grid bag constraint with the specified x and y
     * values.
     * 
     * @param x X-coordinate.
     * @param y Y-coordinate.
     * @return GridBagConstraints
     */
    private GridBagConstraints createConstraints(int x, int y) {

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;

        return constraints;
    }

}