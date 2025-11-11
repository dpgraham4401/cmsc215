/**
 * Undergraduate is a subclass of Student.
 * <p>
 * It has an additional instance variable that specifies the student’s year as either a freshman, sophomore, junior or senior.
 * At a minimum, it should have the following methods:
 * 1. A constructor that allows the student’s name, credit hours, quality points and class rank to be initialized
 * 2. An overridden method eligibleForHonorSociety that applies the requirement that the
 * student be either a junior or senior in addition to the requirement that applies to all
 * students to be eligible for honor society membership.
 * 3. An overridden toString method that returns a string containing the student's name,grade point average, and year.
 */
package com.vimpirate.students;

public class Undergraduate extends Student {
    private final Year year;

    public Undergraduate(String name, int creditHours, int qualityPoints, Year year) {
        super(name, creditHours, qualityPoints);
        this.year = year;
    }

    /**
     * Override to only allow juniors and seniors to be eligible for honor society.
     */
    @Override
    public boolean eligibleForHonorSociety() {
        return super.eligibleForHonorSociety() && (year == Year.JUNIOR || year == Year.SENIOR);
    }

    /**
     * Override toString to include year information.
     */
    @Override
    public String toString() {
        return String.format("%s, GPA: %.2f, Year: %s", getName(), gpa(), year);
    }

    public enum Year {
        FRESHMAN,
        SOPHOMORE,
        JUNIOR,
        SENIOR
    }
}
