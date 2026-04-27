package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static boolean demonstrateLengthEquality(Length l1, Length l2) {
        return l1.equals(l2);
    }

    public static boolean demonstrateLengthComparison(double v1, LengthUnit u1, double v2, LengthUnit u2) {
        Length l1 = new Length(v1, u1);
        Length l2 = new Length(v2, u2);
        return l1.equals(l2);
    }

    public static Length demonstrateLengthConversion(double value, LengthUnit from, LengthUnit to) {
        return new Length(value, from).convertTo(to);
    }

    public static Length demonstrateLengthConversion(Length length, LengthUnit to) {
        return length.convertTo(to);
    }

    public static double demonstrateStaticLengthConversion(double value, LengthUnit from, LengthUnit to) {
        return Length.convert(value, from, to);
    }

    public static Length demonstrateLengthAddition(Length l1, Length l2) {
        return l1.add(l2);
    }

    public static Length demonstrateLengthAddition(Length l1, Length l2, LengthUnit target) {
        return l1.add(l2, target);
    }

    public static Length demonstrateStaticLengthAddition(Length l1, Length l2, LengthUnit target) {
        return Length.add(l1, l2, target);
    }

    public static boolean demonstrateWeightEquality(Weight w1, Weight w2) {
        return w1.equals(w2);
    }

    public static boolean demonstrateWeightComparison(double v1, WeightUnit u1, double v2, WeightUnit u2) {
        Weight w1 = new Weight(v1, u1);
        Weight w2 = new Weight(v2, u2);
        return w1.equals(w2);
    }

    public static Weight demonstrateWeightConversion(double value, WeightUnit from, WeightUnit to) {
        return new Weight(value, from).convertTo(to);
    }

    public static Weight demonstrateWeightConversion(Weight weight, WeightUnit to) {
        return weight.convertTo(to);
    }

    public static Weight demonstrateWeightAddition(Weight w1, Weight w2) {
        return w1.add(w2);
    }

    public static Weight demonstrateWeightAddition(Weight w1, Weight w2, WeightUnit target) {
        return w1.add(w2, target);
    }

    public static Weight demonstrateStaticWeightAddition(Weight w1, Weight w2, WeightUnit target) {
        return Weight.add(w1, w2, target);
    }

    public static void main(String[] args) {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        System.out.println("Length Equality: " + demonstrateLengthEquality(l1, l2));
        System.out.println("Length Conversion (1 ft to inches): " +
                demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCHES));
        System.out.println("Length Addition (default): " +
                demonstrateLengthAddition(l1, l2));
        System.out.println("Length Addition (target YARDS): " +
                demonstrateLengthAddition(l1, l2, LengthUnit.YARDS));

        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1000.0, WeightUnit.GRAM);

        System.out.println("Weight Equality: " + demonstrateWeightEquality(w1, w2));
        System.out.println("Weight Conversion (1 kg to grams): " +
                demonstrateWeightConversion(1.0, WeightUnit.KILOGRAM, WeightUnit.GRAM));
        System.out.println("Weight Addition (default): " +
                demonstrateWeightAddition(w1, w2));
        System.out.println("Weight Addition (target POUND): " +
                demonstrateWeightAddition(w1, w2, WeightUnit.POUND));
    }
}