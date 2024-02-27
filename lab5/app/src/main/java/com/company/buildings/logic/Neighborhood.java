package com.company.buildings.logic;

import com.company.buildings.ui.OutputInterface;

public final class Neighborhood {
    private Neighborhood() {
        // Приватный конструктор, чтобы предотвратить создание экземпляров класса
    }

    public static void print(Building[] buildings, String header, OutputInterface out) {
        out.print(header);
        for (Building building : buildings) {
            out.print(building.toString());
        }
    }

    public static int calcArea(Building[] buildings) {
        int totalArea = 0;
        for (Building building : buildings) {
            totalArea += building.calcLotArea();
        }
        return totalArea;
    }
}
