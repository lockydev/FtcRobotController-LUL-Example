package dev.locky.lulftc.utils;

public class Tolerance {

    public static boolean withinTolerance(double value1, double value2,  double tolerance) {
        return Math.abs(value1 - value2) < tolerance;
    }

    public static boolean withinTolerance(int value1, int value2,  int tolerance) {
        return Math.abs(value1 - value2) < tolerance;
    }

}
