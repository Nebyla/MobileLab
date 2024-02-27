package com.company.buildings.logic;

/**
 * This is the office class file, it is a subclass of Building.
 */
public class Office extends Building {
    private String mBusinessName;
    private int mParkingSpaces;
    private static int sTotalOffices = 0;

    // Первый конструктор
    public Office(int length, int width, int lotLength, int lotWidth) {
        super(length, width, lotLength, lotWidth);
        mBusinessName = null;
        mParkingSpaces = 0;
        sTotalOffices++;
    }

    // Второй конструктор
    public Office(int length, int width, int lotLength, int lotWidth, String businessName) {
        super(length, width, lotLength, lotWidth);
        mBusinessName = businessName;
        mParkingSpaces = 0;
        sTotalOffices++;
    }

    // Третий конструктор
    public Office(int length, int width, int lotLength, int lotWidth, String businessName, int parkingSpaces) {
        super(length, width, lotLength, lotWidth);
        mBusinessName = businessName;
        mParkingSpaces = parkingSpaces;
        sTotalOffices++;
    }

    // Геттер и сеттер для названия бизнеса
    public String getBusinessName() {
        return mBusinessName;
    }

    public void setBusinessName(String businessName) {
        mBusinessName = businessName;
    }

    // Геттер и сеттер для парковочных мест
    public int getParkingSpaces() {
        return mParkingSpaces;
    }

    public void setParkingSpaces(int parkingSpaces) {
        mParkingSpaces = parkingSpaces;
    }

    // Переопределение метода toString()
    @Override
    public String toString() {
        StringBuilder officeString = new StringBuilder("Business: ");
        if (mBusinessName != null) {
            officeString.append(mBusinessName);
        } else {
            officeString.append("unoccupied");
        }

        if (mParkingSpaces > 0) {
            officeString.append("; has ").append(mParkingSpaces).append(" parking spaces");
        }

        officeString.append(" (total offices: ").append(sTotalOffices).append(")");

        return officeString.toString();
    }

    // Переопределение метода equals()
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Office)) return false;

        Office otherOffice = (Office) obj;

        return super.calcBuildingArea() == otherOffice.calcBuildingArea() &&
                this.mParkingSpaces == otherOffice.mParkingSpaces;
    }

    // Геттер для статической переменной sTotalOffices
    public static int getTotalOffices() {
        return sTotalOffices;
    }
}
