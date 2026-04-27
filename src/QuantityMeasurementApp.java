package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static boolean demonstrateLengthEquality(Length l1, Length l2) {
        return l1.equals(l2);
    }

    public static Length demonstrateLengthConversion(double value, Length.LengthUnit from, Length.LengthUnit to) {
        return new Length(value, from).convertTo(to);
    }

    public static Length demonstrateLengthConversion(Length length, Length.LengthUnit to) {
        return length.convertTo(to);
    }

    public static Length demonstrateAddition(Length l1, Length l2) {
        return l1.add(l2);
    }

    public static Length demonstrateAddition(Length l1, Length l2, Length.LengthUnit target) {
        return Length.add(l1, l2, target);
    }

    public static void main(String[] args) {
        Length a = new Length(1.0, Length.LengthUnit.FEET);
        Length b = new Length(12.0, Length.LengthUnit.INCHES);

        System.out.println(demonstrateLengthEquality(a, b));

        System.out.println(demonstrateLengthConversion(1.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES));

        System.out.println(demonstrateAddition(a, b));

        System.out.println(demonstrateAddition(a, b, Length.LengthUnit.YARDS));
    }
}