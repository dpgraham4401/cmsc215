package com.vimpirate;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Name: David Graham
 * Description: A GUI application to compare time intervals.
 */
public class Project4 extends Application {

    private final TextField interval1StartField = new TextField("10:30 AM");
    private final TextField interval1EndField = new TextField("12:30 PM");
    private final TextField interval2StartField = new TextField("11:05 AM");
    private final TextField interval2EndField = new TextField("1:00 PM");

    private final TextField checkTimeField = new TextField("");

    private final TextField outputField = new TextField("");

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Time Interval Checker");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(12);
        grid.setVgap(12);
        grid.setPadding(new Insets(20));

        ColumnConstraints col0 = new ColumnConstraints(130);
        ColumnConstraints col1 = new ColumnConstraints(150);
        ColumnConstraints col2 = new ColumnConstraints(150);
        grid.getColumnConstraints().addAll(col0, col1, col2);

        // Headings
        Label startHeading = new Label("Start Time");
        Label endHeading = new Label("End Time");
        startHeading.setFont(Font.font(14));
        endHeading.setFont(Font.font(14));

        grid.add(startHeading, 1, 0);
        grid.add(endHeading, 2, 0);

        // Interval 1
        grid.add(new Label("Time Interval 1"), 0, 1);
        grid.add(interval1StartField, 1, 1);
        grid.add(interval1EndField, 2, 1);

        // Interval 2
        grid.add(new Label("Time Interval 2"), 0, 2);
        grid.add(interval2StartField, 1, 2);
        grid.add(interval2EndField, 2, 2);

        // Compare button
        Button compareButton = new Button("Compare Intervals");
        compareButton.setPrefWidth(300);
        compareButton.setOnAction(this::handleCompareIntervals);

        HBox compareBox = new HBox(compareButton);
        compareBox.setAlignment(Pos.CENTER);
        grid.add(compareBox, 0, 3, 3, 1);

        // Time to check
        grid.add(new Label("Time to Check"), 0, 4);
        grid.add(checkTimeField, 1, 4, 2, 1);

        // Check time button
        Button checkTimeButton = new Button("Check Time");
        checkTimeButton.setPrefWidth(300);
        checkTimeButton.setOnAction(this::handleCheckTime);

        HBox checkBox = new HBox(checkTimeButton);
        checkBox.setAlignment(Pos.CENTER);
        grid.add(checkBox, 0, 5, 3, 1);

        // Output text box (non-editable)
        outputField.setEditable(false);
        outputField.setFont(Font.font(14));

        grid.add(outputField, 0, 6, 3, 1);

        Scene scene = new Scene(grid, 500, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleCompareIntervals(ActionEvent event) {
        try {
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
            outputField.setText(message);

        } catch (InvalidTime e) {
            outputField.setText("Error: " + e.getMessage());
        }
    }

    private void handleCheckTime(ActionEvent event) {
        try {
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

            outputField.setText(message);

        } catch (InvalidTime e) {
            outputField.setText("Error: " + e.getMessage());
        }
    }

    private Interval<Time> getInterval(TextField startField, TextField endField) throws InvalidTime {
        return new Interval<>(new Time(startField.getText()), new Time(endField.getText()));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
