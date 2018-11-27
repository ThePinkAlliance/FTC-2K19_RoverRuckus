package org.firstinspires.PinkCode.Calculations;

public abstract class Range {
    // Method to Check If a Value is Within a Range
    public static boolean in_range (double checked_number, double minimum, double maximum) {
        return checked_number > minimum && checked_number < maximum;
    }

    // Method to Check If a Value is Outside a Range
    public static boolean out_range (double checked_number, double minimum, double maximum) {
        return checked_number < minimum || checked_number > maximum;
    }

}