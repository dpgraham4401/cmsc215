/**
 * Project3
 * Author: Your Name
 * Date: 2025-02-01
 * <p>
 * Purpose:
 *     GUI program that allows a user to estimate total road trip cost using
 *     combinations of Imperial or Metric units for distance, fuel cost, and gas mileage.
 */
package com.vimpirate;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class Project3 extends JFrame {

    // Input fields
    private final JTextField distanceField = new JTextField(10);
    private final JTextField gasolineCostField = new JTextField(10);
    private final JTextField mileageField = new JTextField(10);
    private final JTextField daysField = new JTextField(10);
    private final JTextField hotelField = new JTextField(10);
    private final JTextField foodField = new JTextField(10);
    private final JTextField attractField = new JTextField(10);

    // Combo boxes
    private final JComboBox<String> distUnits =
            new JComboBox<>(new String[]{"miles", "kilometers"});
    private final JComboBox<String> gasUnits =
            new JComboBox<>(new String[]{"dollars/gal", "dollars/liter"});
    private final JComboBox<String> mpgUnits =
            new JComboBox<>(new String[]{"miles/gallon", "kms/liter"});

    // Output field
    private final JTextField outputField = new JTextField(12);

    public Project3() {
        super("Trip Cost Estimator");

        setLayout(new GridLayout(9, 3, 5, 5));

        addRow("Distance:", distanceField, distUnits);
        addRow("Gasoline Cost:", gasolineCostField, gasUnits);
        addRow("Gas Mileage:", mileageField, mpgUnits);
        addRow("Number Of Days:", daysField, null);
        addRow("Hotel Cost:", hotelField, null);
        addRow("Food Cost:", foodField, null);
        addRow("Attractions:", attractField, null);

        JButton calcButton = new JButton("Calculate");
        calcButton.addActionListener(e -> calculate());
        add(calcButton);
        outputField.setEditable(false);
        add(new JLabel("Total Trip Cost"));
        add(outputField);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 350);
        setVisible(true);
    }

    /** Helper method to add a labeled row */
    private void addRow(String label, JTextField field, JComboBox<String> box) {
        add(new JLabel(label));
        add(field);
        if (box != null) add(box);
        else add(new JLabel(""));
    }

    /** Convert all units and build TripCost display formatted total */
    private void calculate() {
        try {
            double distance = Double.parseDouble(distanceField.getText());
            double gasCost = Double.parseDouble(gasolineCostField.getText());
            double mileage = Double.parseDouble(mileageField.getText());
            int days = Integer.parseInt(daysField.getText());
            double hotel = Double.parseDouble(hotelField.getText());
            double food = Double.parseDouble(foodField.getText());
            double attractions = Double.parseDouble(attractField.getText());

            // Unit conversions
            if (Objects.equals(distUnits.getSelectedItem(), "kilometers"))
                distance /= TripCost.KILOMETERS_PER_MILE;

            if (Objects.equals(gasUnits.getSelectedItem(), "dollars/liter"))
                gasCost *= TripCost.LITERS_PER_GALLON;

            if (Objects.equals(mpgUnits.getSelectedItem(), "kms/liter")) {
                mileage *= (TripCost.KILOMETERS_PER_MILE / TripCost.LITERS_PER_GALLON);
            }

            TripCost trip = new TripCost(distance, gasCost, mileage,
                    days, hotel, food, attractions);

            double total = trip.computeTotalCost();
            outputField.setText(String.format("$%.2f", total));

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter valid numeric values.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new Project3();
    }
}
