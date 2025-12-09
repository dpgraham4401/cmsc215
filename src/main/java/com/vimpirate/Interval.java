package com.vimpirate;

/**
 * Name: David Graham
 * Description: A generic class representing an interval.
 */
public class Interval<T extends Comparable<T>> {

    private final T start;
    private final T end;

    /**
     * Constructs an Interval object.
     *
     * @param start The start of the interval.
     * @param end   The end of the interval.
     */
    public Interval(T start, T end) {
        if (start.compareTo(end) > 0) {
            this.start = end;
            this.end = start;
        } else {
            this.start = start;
            this.end = end;
        }
    }

    /**
     * Checks if a point is within the interval.
     *
     * @param point The point to check.
     * @return True if the point is within the interval, false otherwise.
     */
    public boolean within(T point) {
        return point.compareTo(start) >= 0 && point.compareTo(end) <= 0;
    }

    /**
     * Checks if another interval is a subinterval of this interval.
     *
     * @param other The other interval to check.
     * @return True if the other interval is a subinterval, false otherwise.
     */
    public boolean subinterval(Interval<T> other) {
        return this.within(other.start) && this.within(other.end);
    }

    /**
     * Checks if another interval overlaps with this interval.
     *
     * @param other The other interval to check.
     * @return True if the intervals overlap, false otherwise.
     */
    public boolean overlaps(Interval<T> other) {
        return this.within(other.start) || this.within(other.end) || other.within(this.start) || other.within(this.end);
    }

    @Override
    public String toString() {
        return "[" + start + ", " + end + "]";
    }
}
