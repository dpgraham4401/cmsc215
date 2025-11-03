/**
 *
 * The first
 * class is the Student class. It has three instance variables which include the student's name, credit
 * hours earned and quality points.
 * A constructor that allows the student’s name, credit hours and quality points to be
initialized.
• A method named gpa that returns the student’s grade point average, which is computed
as the quotient of the quality points and credit hours.
• A method eligibleForHonorSociety that returns whether a student’s gpa exceeds the
threshold for honor society membership, which applies to all students.
• A toString method that returns a string containing the student's name and grade point
average.
• A class (static) method setGpaThreshold that sets the minimum gpa threshold for honor
society membership. The value will be assigned after all student information has been
read in.
 *
 */
package com.vimpirate.students;

/**
 * Student class representing a student with name, credit hours, and quality points.
 */
public class Student {
    private final String name;
    private final int creditHours;
    private final double qualityPoints;
    private static double gpaThreshold;

    public Student(String name, int creditHours, double qualityPoints) {
        this.name = name;
        this.creditHours = creditHours;
        this.qualityPoints = qualityPoints;
    }

    /**
     * Calculate the GPA.
     * quality points = the credit hours * grade points (e.g., 3 hour course with an A, or 4.0, = 12 quality points)
     * credit hours = total number of credit hours attempted
     * GPA = quality points / credit hours
     */
    public double gpa() {
        if (creditHours == 0) {
            return 0.0;
        }
        return qualityPoints / creditHours;
    }

    /**
     * Check if the student is eligible for honor society based on GPA threshold.
     */
    public boolean eligibleForHonorSociety() {
        return gpa() >= gpaThreshold;
    }

    public static void setGpaThreshold(double threshold) {
        gpaThreshold = threshold;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", GPA: " + String.format("%.2f", gpa());
    }
}
