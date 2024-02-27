package com.company.buildings.logic;

/**
 * This is the cottage class file. It is a subclass of House.
 */
public class Cottage extends House {
    // Fields
    private final boolean mSecondFloor;

    // Constructors
    public Cottage(int dimension, int lotLength, int lotWidth) {
        super(dimension, dimension, lotLength, lotWidth);
        mSecondFloor = false;
    }

    public Cottage(int dimension, int lotLength, int lotWidth, String owner, boolean secondFloor) {
        super(dimension, dimension, lotLength, lotWidth, owner, false); // Second floor always false for Cottage
        mSecondFloor = secondFloor;
    }

    // Getter method for mSecondFloor
    public boolean hasSecondFloor() {
        return mSecondFloor;
    }

    // Override toString method
    @Override
    public String toString() {
        String houseString = super.toString();
        return String.format("%s, Has Second Floor: %b", houseString, mSecondFloor);
    }
}
