/**
 * TripCost
 * Author: David Graham
 * Date: 2025-11-21
 * <p>
 * Purpose:
 *     Immutable object that stores trip information and computes
 *     the total cost of a road trip based on converted, uniform units.
 */

package com.vimpirate;

public final class TripCost {

    // Unit conversion constants
    public static final double KILOMETERS_PER_MILE = 1.609347;
    public static final double LITERS_PER_GALLON = 3.78541178;

    private final double distanceMiles;        // always stored in miles
    private final double gasCostPerGallon;     // always stored in dollars per gallon
    private final double mileageMpg;           // always stored in miles per gallon
    private final int numDays;
    private final double hotelCost;
    private final double foodCost;
    private final double attractionCost;

    /**
     * Constructs an immutable TripCost object. All values passed in should
     * already be converted into Imperial units (miles, dollars/gal, mpg).
     */
    public TripCost(double distMiles, double gasCostPG, double mpg,
                    int days, double hotel, double food, double attractions) {
        this.distanceMiles = distMiles;
        this.gasCostPerGallon = gasCostPG;
        this.mileageMpg = mpg;
        this.numDays = days;
        this.hotelCost = hotel;
        this.foodCost = food;
        this.attractionCost = attractions;
    }

    /**
     * Computes and returns the total trip cost.
     */
    public double computeTotalCost() {
        double gasolineCost = (distanceMiles / mileageMpg) * gasCostPerGallon;
        double dailyCosts = (hotelCost + foodCost) * numDays;
        return gasolineCost + dailyCosts + attractionCost;
    }
}
