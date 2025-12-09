package com.vimpirate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A GUI application to compare time intervals.
 *
 * @author David Graham
 */
public class Project4 extends JFrame implements ActionListener {

    // GUI Components
    private final JTextField interval1StartField = new JTextField("09:00 AM", 10);
    private final JTextField interval1EndField = new JTextField("05:00 PM", 10);
    private final JTextField interval2StartField = new JTextField("11:00 AM", 10);
    private final JTextField interval2EndField = new JTextField("01:00 PM", 10);
    private final JTextField checkTimeField = new JTextField("12:00 PM", 10);

    private final JButton compareButton = new JButton("Compare Intervals");
    private final JButton checkTimeButton = new JButton("Check Time");

    /**
     * Constructs the GUI.
     */
    public Project4() {
        setTitle("Time Interval Tester");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Interval 1
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Interval 1:"), gbc);
        gbc.gridx = 1;
        add(interval1StartField, gbc);
        gbc.gridx = 2;
        add(new JLabel("to"), gbc);
        gbc.gridx = 3;
        add(interval1EndField, gbc);

        // Interval 2
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Interval 2:"), gbc);
        gbc.gridx = 1;
        add(interval2StartField, gbc);
        gbc.gridx = 2;
        add(new JLabel("to"), gbc);
        gbc.gridx = 3;
        add(interval2EndField, gbc);

        // Check Time
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Check Time:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        add(checkTimeField, gbc);
        gbc.gridwidth = 1;

        // Buttons
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(compareButton, gbc);
        gbc.gridx = 2;
        gbc.gridwidth = 2;
        add(checkTimeButton, gbc);

        // Add action listeners
        compareButton.addActionListener(this);
        checkTimeButton.addActionListener(this);

        pack();
        setLocationRelativeTo(null); // Center the frame
    }

    /**
     * Handles button clicks.
     *
     * @param e The ActionEvent.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == compareButton) {
                handleCompareIntervals();
            } else if (e.getSource() == checkTimeButton) {
                handleCheckTime();
            }
        } catch (InvalidTime ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Invalid Time", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleCompareIntervals() throws InvalidTime {
        Interval<Time> interval1 = getInterval(interval1StartField, interval1EndField);
        Interval<Time> interval2 = getInterval(interval2StartField, interval2EndField);

        String message;
        if (interval2.subinterval(interval1)) {
            message = "Interval 1 is a sub-interval of interval 2";
        } else if (interval1.subinterval(interval2)) {
            message = "Interval 2 is a sub-interval of interval 1";
        } else if (interval1.overlaps(interval2)) {
            message = "The intervals overlap";
        } else {
            message = "The intervals are disjoint";
        }
        JOptionPane.showMessageDialog(this, message, "Comparison Result", JOptionPane.INFORMATION_MESSAGE);
    }

    private void handleCheckTime() throws InvalidTime {
        Time timeToCheck = new Time(checkTimeField.getText());
        Interval<Time> interval1 = getInterval(interval1StartField, interval1EndField);
        Interval<Time> interval2 = getInterval(interval2StartField, interval2EndField);

        boolean inInterval1 = interval1.within(timeToCheck);
        boolean inInterval2 = interval2.within(timeToCheck);

        String message;
        if (inInterval1 && inInterval2) {
            message = "Both intervals contain the time " + timeToCheck;
        } else if (inInterval1) {
            message = "Only interval 1 contains the time " + timeToCheck;
        } else if (inInterval2) {
            message = "Only interval 2 contains the time " + timeToCheck;
        } else {
            message = "Neither interval contains the time " + timeToCheck;
        }
        JOptionPane.showMessageDialog(this, message, "Time Check Result", JOptionPane.INFORMATION_MESSAGE);
    }

    private Interval<Time> getInterval(JTextField startField, JTextField endField) throws InvalidTime {
        Time startTime = new Time(startField.getText());
        Time endTime = new Time(endField.getText());
        return new Interval<>(startTime, endTime);
    }

    /**
     * The main method.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Use the Event Dispatch Thread for Swing components
        SwingUtilities.invokeLater(() -> {
            Project4 frame = new Project4();
            frame.setVisible(true);
        });
    }
}
