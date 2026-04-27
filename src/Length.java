package com.apps.quantitymeasurement;

public class Length {
    private final double value;
    private final LengthUnit unit;

    public enum LengthUnit {
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double factor;

        LengthUnit(double factor) {
            this.factor = factor;
        }

        public double getFactor() {
            return factor;
        }
    }

    public Length(double value, LengthUnit unit) {
        if (!Double.isFinite(value)) throw new IllegalArgumentException();
        if (unit == null) throw new IllegalArgumentException();
        this.value = value;
        this.unit = unit;
    }

    private double toBase() {
        return value * unit.getFactor();
    }

    public Length convertTo(LengthUnit target) {
        if (target == null) throw new IllegalArgumentException();
        double base = toBase();
        double result = base / target.getFactor();
        return new Length(result, target);
    }

    public static double convert(double value, LengthUnit source, LengthUnit target) {
        if (!Double.isFinite(value)) throw new IllegalArgumentException();
        if (source == null || target == null) throw new IllegalArgumentException();
        double base = value * source.getFactor();
        return base / target.getFactor();
    }

    public Length add(Length other) {
        if (other == null) throw new IllegalArgumentException();
        double sumBase = this.toBase() + other.toBase();
        double result = sumBase / this.unit.getFactor();
        return new Length(result, this.unit);
    }

    public static Length add(Length l1, Length l2, LengthUnit target) {
        if (l1 == null || l2 == null) throw new IllegalArgumentException();
        if (target == null) throw new IllegalArgumentException();
        double sumBase = l1.toBase() + l2.toBase();
        double result = sumBase / target.getFactor();
        return new Length(result, target);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Length that = (Length) obj;
        return Double.compare(this.toBase(), that.toBase()) == 0;
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}