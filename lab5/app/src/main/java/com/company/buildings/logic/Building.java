package com.company.buildings.logic;

public class Building {
    private int mLength;
    private int mWidth;
    private int mLotLength;
    private int mLotWidth;

    // Конструктор
    public Building(int length, int width, int lotLength, int lotWidth) {
        mLength = length;
        mWidth = width;
        mLotLength = lotLength;
        mLotWidth = lotWidth;
    }

    // Геттеры
    public int getLength() {
        return mLength;
    }

    public int getWidth() {
        return mWidth;
    }

    public int getLotLength() {
        return mLotLength;
    }

    public int getLotWidth() {
        return mLotWidth;
    }

    // Сеттеры
    public void setLength(int length) {
        mLength = length;
    }

    public void setWidth(int width) {
        mWidth = width;
    }

    public void setLotLength(int lotLength) {
        mLotLength = lotLength;
    }

    public void setLotWidth(int lotWidth) {
        mLotWidth = lotWidth;
    }

    // Методы для вычисления площади здания и участка
    public int calcBuildingArea() {
        return mLength * mWidth;
    }

    public int calcLotArea() {
        return mLotLength * mLotWidth;
    }

    // Метод toString() для представления объекта в виде строки
    @Override
    public String toString() {
        return "Building [Length=" + mLength + ", Width=" + mWidth +
                ", LotLength=" + mLotLength + ", LotWidth=" + mLotWidth + "]";
    }
}
