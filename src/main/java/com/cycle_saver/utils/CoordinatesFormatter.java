package com.cycle_saver.utils;

import java.util.List;

public class CoordinatesFormatter {
    public static String formatCoords(List<Double> coordinate) {
        return coordinate.get(0).toString() + "," + coordinate.get(1).toString();
    }
}
