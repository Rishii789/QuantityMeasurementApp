package com.apps.quantitymeasurement;

public class Length {
    private final double value;
    private final LengthUnit unit;

    public Length(double value, LengthUnit unit) {
        if (!Double.isFinite(value)) throw new IllegalArgumentException();
        if (unit == null) throw new IllegalArgumentException();
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    private double toBase() {
        return unit.convertToBaseUnit(value);
    }

    public Length convertTo(LengthUnit target) {
        if (target == null) throw new IllegalArgumentException();
        double base = toBase();
        double converted = target.convertFromBaseUnit(base);
        return new Length(converted, target);
    }

    public static double convert(double value, LengthUnit source, LengthUnit target) {
        if (!Double.isFinite(value)) throw new IllegalArgumentException();
        if (source == null || target == null) throw new IllegalArgumentException();
        double base = source.convertToBaseUnit(value);
        return target.convertFromBaseUnit(base);
    }

    public Length add(Length other) {
        if (other == null) throw new IllegalArgumentException();
        double sumBase = this.toBase() + other.toBase();
        double result = unit.convertFromBaseUnit(sumBase);
        return new Length(result, this.unit);
    }

    public Length add(Length other, LengthUnit target) {
        if (other == null) throw new IllegalArgumentException();
        if (target == null) throw new IllegalArgumentException();
        double sumBase = this.toBase() + other.toBase();
        double result = target.convertFromBaseUnit(sumBase);
        return new Length(result, target);
    }

    public static Length add(Length l1, Length l2, LengthUnit target) {
        if (l1 == null || l2 == null) throw new IllegalArgumentException();
        if (target == null) throw new IllegalArgumentException();
        double sumBase = l1.toBase() + l2.toBase();
        double result = target.convertFromBaseUnit(sumBase);
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
    public int hashCode() {
        return Double.hashCode(toBase());
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}