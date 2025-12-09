package com.vimpirate;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * A GUI application to compare time intervals using JavaFX.
 *
 * @author Gemini
 * @version 2025-12-09
 */
public class Project4 extends Application {

    // GUI Components
    private final TextField interval1StartField = new TextField("09:00 AM");
    private final TextField interval1EndField = new TextField("05:00 PM");
    private final TextField interval2StartField = new TextField("11:00 AM");
    private final TextField interval2EndField = new TextField("01:00 PM");
    private final TextField checkTimeField = new TextField("12:00 PM");

    /**
     * The main entry point for all JavaFX applications.
     *
     * @param primaryStage the primary stage for this application.
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Time Interval Tester");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Interval 1
        grid.add(new Label("Interval 1:"), 0, 0);
        grid.add(interval1StartField, 1, 0);
        grid.add(new Label("to"), 2, 0);
        grid.add(interval1EndField, 3, 0);

        // Interval 2
        grid.add(new Label("Interval 2:"), 0, 1);
        grid.add(interval2StartField, 1, 1);
        grid.add(new Label("to"), 2, 1);
        grid.add(interval2EndField, 3, 1);

        // Check Time
        grid.add(new Label("Check Time:"), 0, 2);
        grid.add(checkTimeField, 1, 2, 3, 1);

        // Buttons
        Button compareButton = new Button("Compare Intervals");
        Button checkTimeButton = new Button("Check Time");
        compareButton.setOnAction(this::handleCompareIntervals);
        checkTimeButton.setOnAction(this::handleCheckTime);

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(compareButton, checkTimeButton);
        grid.add(buttonBox, 0, 3, 4, 1);

        Scene scene = new Scene(grid);
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
            showAlert(Alert.AlertType.INFORMATION, "Comparison Result", message);
        } catch (InvalidTime e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Time", "Error: " + e.getMessage());
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
            showAlert(Alert.AlertType.INFORMATION, "Time Check Result", message);
        } catch (InvalidTime e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Time", "Error: " + e.getMessage());
        }
    }

    private Interval<Time> getInterval(TextField startField, TextField endField) throws InvalidTime {
        Time startTime = new Time(startField.getText());
        Time endTime = new Time(endField.getText());
        return new Interval<>(startTime, endTime);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * The main method.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        launch(args);
    }
}