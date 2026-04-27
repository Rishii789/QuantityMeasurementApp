package com.apps.quantitymeasurement;

public class Length {
    private final double value;
    private final LengthUnit unit;

    public enum LengthUnit {
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    public Length(double value, LengthUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    private double toBaseUnit() {
        return this.value * this.unit.getConversionFactor();
    }

    private boolean compare(Length thatLength) {
        return Double.compare(this.toBaseUnit(), thatLength.toBaseUnit()) == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Length that = (Length) o;
        if (this.unit == null || that.unit == null) return false;
        return compare(that);
    }

    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException();
        double base = toBaseUnit();
        double converted = base / targetUnit.getConversionFactor();
        return new Length(converted, targetUnit);
    }

    public static double convert(double value, LengthUnit source, LengthUnit target) {
        if (!Double.isFinite(value)) throw new IllegalArgumentException();
        if (source == null || target == null) throw new IllegalArgumentException();
        double base = value * source.getConversionFactor();
        return base / target.getConversionFactor();
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit);
    }

    public static void main(String[] args) {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);
        System.out.println(l1.equals(l2));
        System.out.println(convert(3.0, LengthUnit.FEET, LengthUnit.INCHES));
    }
}