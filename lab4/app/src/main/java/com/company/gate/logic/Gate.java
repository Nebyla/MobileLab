package com.company.gate.logic;

public class Gate {
    public static final int IN = 1;
    public static final int OUT = -1;
    public static final int CLOSED = 0;

    private int mSwing;
    public Gate() {
        mSwing = CLOSED;
    }

    /**
     * Sets the swing direction of the gate.
     *
     * @param direction The swing direction to set (IN, OUT, or CLOSED).
     * @return true if the swing direction is set successfully, false otherwise.
     */
    public boolean setSwing(int direction) {
        if (direction == IN || direction == OUT || direction == CLOSED) {
            mSwing = direction;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Opens the gate in the specified direction.
     *
     * @param direction The direction to open the gate (IN or OUT).
     * @return true if the gate is successfully opened, false otherwise.
     */
    public boolean open(int direction) {
        if (direction == IN || direction == OUT) {
            return setSwing(direction);
        } else {
            return false;
        }
    }

    /**
     * Closes the gate.
     */
    public void close() {
        mSwing = CLOSED;
    }

    /**
     * Gets the current swing direction of the gate.
     *
     * @return The current swing direction (IN, OUT, or CLOSED).
     */
    public int getSwingDirection() {
        return mSwing;
    }

    /**
     * Manages the movement of snails through the gate.
     *
     * @param count The number of snails.
     * @return The adjusted count based on the gate's swing direction.
     */
    public int thru(int count) {
        if (mSwing == IN) {
            return count;
        } else if (mSwing == OUT) {
            return -count;
        } else {
            return 0;
        }
    }

    /**
     * Returns a string representation of the gate.
     *
     * @return The string representation of the gate.
     */
    @Override
    public String toString() {
        if (mSwing == CLOSED) {
            return "This gate is closed";
        } else if (mSwing == IN) {
            return "This gate is open and swings to enter the pen only";
        } else if (mSwing == OUT) {
            return "This gate is open and swings to exit the pen only";
        } else {
            return "This gate has an invalid swing direction";
        }
    }
}
