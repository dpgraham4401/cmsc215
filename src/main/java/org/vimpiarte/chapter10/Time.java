package org.vimpiarte.chapter10;

/**
 * CMSC 215 Chapter 10 - Project # 1
 * <p>
 * Design a class named Time. The class contains:
 * <p>
 * 1. The data fields hour, minute, and second that represent a time.
 * <p>
 * 2. A no-arg constructor that creates a Time object for the current time.
 * (The values of the data fields will represent the current time)
 * <p>
 * 3. A constructor that constructs a Time object with a specified elapsed time since midnight, January 1, 1970, in milliseconds.
 * (The values of the data fields will represent this time)
 * <p>
 * 4. A constructor that constructs a Time object with the specified hour, minute, and second.
 * <p>
 * 5. Three getter methods for the data fields hour, minute, and second, respectively.
 * <p>
 * 6. A method named setTime(long elapseTime) that sets a new time for the object using the elapsed time.
 * For example, if the elapsed time is 555550000 milliseconds, the hour is 10, the minute is 19, and the second is 10
 *
 */
class Time {
    private int hour;
    private int minute;
    private int second;

    /**
     * If no arguments are provided, use the current system time by default.
     */
    public Time() {
        this(System.currentTimeMillis());
    }

    /**
     * A constructor that creates a Time object from the elapsed time
     * @param elapsedTime time in milliseconds
     */
    public Time(long elapsedTime) {
        setTime(elapsedTime);
    }

    /**
     * A constructor that creates a Time object with the specified hour, minute, and second
     * @param hour hour of the time
     * @param minute minute of the time
     * @param second second of the time
     */
    public Time(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public void setTime(long elapsedTime) {
        // there are 1000 milliseconds in a second
        long totalSeconds = elapsedTime / 1000;
        // 60 seconds in a minute, the modulus operator gives the current second
        this.second = (int)(totalSeconds % 60);
        long totalMinutes = totalSeconds / 60;
        // the remainder of number of seconds/seconds per minutes = current minute
        this.minute = (int)(totalMinutes % 60);
        long totalHours = totalMinutes / 60;
        // Compute current hour in 24-hour format
        this.hour = (int)(totalHours % 24);
    }

}