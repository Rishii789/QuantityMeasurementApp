package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        return length1.equals(length2);
    }

    public static boolean demonstrateLengthComparison(double v1, Length.LengthUnit u1, double v2, Length.LengthUnit u2) {
        Length l1 = new Length(v1, u1);
        Length l2 = new Length(v2, u2);
        return l1.equals(l2);
    }

    public static Length demonstrateLengthConversion(double value, Length.LengthUnit fromUnit, Length.LengthUnit toUnit) {
        Length l = new Length(value, fromUnit);
        return l.convertTo(toUnit);
    }

    public static Length demonstrateLengthConversion(Length length, Length.LengthUnit toUnit) {
        return length.convertTo(toUnit);
    }

    public static Length demonstrateAddition(Length l1, Length l2) {
        return l1.add(l2);
    }

    public static Length demonstrateAddition(Length l1, Length l2, Length.LengthUnit targetUnit) {
        return Length.add(l1, l2, targetUnit);
    }

    public static void main(String[] args) {
        Length a = new Length(1.0, Length.LengthUnit.FEET);
        Length b = new Length(12.0, Length.LengthUnit.INCHES);
        System.out.println(demonstrateLengthEquality(a, b));

        System.out.println(demonstrateLengthConversion(1.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES));

        Length c = new Length(1.0, Length.LengthUnit.YARDS);
        Length d = new Length(3.0, Length.LengthUnit.FEET);
        System.out.println(demonstrateAddition(c, d));

        System.out.println(demonstrateAddition(a, b, Length.LengthUnit.FEET));
    }
}