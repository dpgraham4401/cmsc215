/**
 * Graduate class
 *
 * @author David Graham
 * date: 2025-11-11
 * CMSC 215 - Project 2
 * <p>
 * Graduate. It has an additional instance variable that reflects whether the student is pursuing a master’s degree or doctorate.
 * At a minimum, it should have the following methods:
 * 1. A constructor that allows the student’s name, credit hours, quality points and degree sought to be initialized
 * 2. An overridden method eligibleForHonorSociety that applies the requirement that the
 * student be seeking a master’s degree in addition to the requirement that applies to all
 * students to be eligible for honor society membership
 * 3. An overridden toString method that returns a string containing the student's name, grade point average, and degree sought
 */
package com.vimpirate.students;

public class Graduate extends Student {
    private final Degree degree;

    public Graduate(String name, int creditHours, int qualityPoints, Degree degree) {
        super(name, creditHours, qualityPoints);
        this.degree = degree;
    }

    /**
     * Override to only allow master's degree seekers to be eligible for honor society.
     */
    @Override
    public boolean eligibleForHonorSociety() {
        return super.eligibleForHonorSociety() && (degree == Degree.MASTERS);
    }

    /**
     * Override toString to include degree information.
     */
    @Override
    public String toString() {
        return String.format("%s, GPA: %.2f, Degree: %s", getName(), gpa(), degree);
    }

    public enum Degree {
        MASTERS,
        DOCTORATE
    }
}
