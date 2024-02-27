package com.company.buildings.logic;
import java.util.Objects;

/**
 * This is the House class file that extends Building.
 */
public class House extends Building {
    private String mOwner;
    private boolean mPool;

    // Первый конструктор
    public House(int length, int width, int lotLength, int lotWidth) {
        super(length, width, lotLength, lotWidth);
        mOwner = null;
        mPool = false;
    }

    // Второй конструктор
    public House(int length, int width, int lotLength, int lotWidth, String owner) {
        super(length, width, lotLength, lotWidth);
        mOwner = owner;
        mPool = false;
    }

    // Третий конструктор
    public House(int length, int width, int lotLength, int lotWidth, String owner, boolean pool) {
        super(length, width, lotLength, lotWidth);
        mOwner = owner;
        mPool = pool;
    }

    // Геттер и сеттер для владельца
    public String getOwner() {
        return mOwner;
    }

    public void setOwner(String owner) {
        mOwner = owner;
    }

    // Геттер и сеттер для бассейна
    public boolean hasPool() {
        return mPool;
    }

    public void setPool(boolean pool) {
        mPool = pool;
    }

    // Переопределение метода toString()
    @Override
    public String toString() {
        StringBuilder houseString = new StringBuilder("Owner: ");
        if (mOwner != null) {
            houseString.append(mOwner);
        } else {
            houseString.append("n/a");
        }

        if (mPool) {
            houseString.append("; has a pool");
        }

        if (calcLotArea() > calcBuildingArea()) {
            houseString.append("; has a big open space");
        }

        return houseString.toString();
    }

    // Переопределение метода equals()
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof House)) return false;

        House otherHouse = (House) obj;

        return super.calcBuildingArea() == otherHouse.calcBuildingArea() &&
                this.mPool == otherHouse.mPool;
    }
}
