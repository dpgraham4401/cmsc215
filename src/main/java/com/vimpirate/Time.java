package com.vimpirate;

import java.util.Objects;

/**
 * Name: David Graham
 * Description: A class representing time in HH:MM AM/PM format.
 */
public class Time implements Comparable<Time> {

    private final int hours;
    private final int minutes;
    private final String meridian; // AM or PM

    /**
     * Constructs a Time object.
     *
     * @param hours    The hours (1-12).
     * @param minutes  The minutes (0-59).
     * @param meridian The meridian ("AM" or "PM").
     * @throws InvalidTime if the time is invalid.
     */
    public Time(int hours, int minutes, String meridian) throws InvalidTime {
        if (hours < 1 || hours > 12) {
            throw new InvalidTime("Hours must be between 1 and 12.");
        }
        if (minutes < 0 || minutes > 59) {
            throw new InvalidTime("Minutes must be between 0 and 59.");
        }
        if (!"AM".equalsIgnoreCase(meridian) && !"PM".equalsIgnoreCase(meridian)) {
            throw new InvalidTime("Meridian must be AM or PM.");
        }
        this.hours = hours;
        this.minutes = minutes;
        this.meridian = meridian.toUpperCase();
    }

    /**
     * Constructs a Time object from a string.
     *
     * @param timeString The time in "HH:MM AM/PM" format.
     * @throws InvalidTime if the time string is invalid.
     */
    public Time(String timeString) throws InvalidTime {
        if (timeString == null || !timeString.matches("\\d{1,2}:\\d{2}\\s+(AM|PM|am|pm)")) {
            throw new InvalidTime("Invalid time format. Use HH:MM AM/PM.");
        }

        String[] parts = timeString.split("[:\\s]+");
        try {
            int parsedHours = Integer.parseInt(parts[0]);
            int parsedMinutes = Integer.parseInt(parts[1]);
            String parsedMeridian = parts[2];

            if (parsedHours < 1 || parsedHours > 12) {
                throw new InvalidTime("Hours must be between 1 and 12.");
            }
            if (parsedMinutes < 0 || parsedMinutes > 59) {
                throw new InvalidTime("Minutes must be between 0 and 59.");
            }
            if (!"AM".equalsIgnoreCase(parsedMeridian) && !"PM".equalsIgnoreCase(parsedMeridian)) {
                throw new InvalidTime("Meridian must be AM or PM.");
            }

            this.hours = parsedHours;
            this.minutes = parsedMinutes;
            this.meridian = parsedMeridian.toUpperCase();
        } catch (NumberFormatException e) {
            throw new InvalidTime("Hours and minutes must be numeric.");
        }
    }

    /**
     * Compares two Time objects.
     *
     * @param other The other Time object to compare to.
     * @return -1, 0, or 1 if this time is before, equal to, or after the other time.
     */
    @Override
    public int compareTo(Time other) {
        int this24Hour = this.to24Hour();
        int other24Hour = other.to24Hour();

        if (this24Hour != other24Hour) {
            return Integer.compare(this24Hour, other24Hour);
        }
        return Integer.compare(this.minutes, other.minutes);
    }

    private int to24Hour() {
        if (this.meridian.equals("AM")) {
            return (this.hours == 12) ? 0 : this.hours; // 12 AM is 0 hour
        } else { // PM
            return (this.hours == 12) ? 12 : this.hours + 12; // 12 PM is 12 hour
        }
    }

    /**
     * Returns the string representation of the time.
     *
     * @return The time in "HH:MM AM/PM" format.
     */
    @Override
    public String toString() {
        return String.format("%02d:%02d %s", hours, minutes, meridian);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return hours == time.hours && minutes == time.minutes && meridian.equals(time.meridian);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hours, minutes, meridian);
    }
}
