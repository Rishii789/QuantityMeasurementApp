package com.apps.quantitymeasurement;

public class Weight {
    private final double value;
    private final WeightUnit unit;

    public Weight(double value, WeightUnit unit) {
        if (!Double.isFinite(value)) throw new IllegalArgumentException();
        if (unit == null) throw new IllegalArgumentException();
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    private double toBase() {
        return unit.convertToBaseUnit(value);
    }

    public Weight convertTo(WeightUnit target) {
        if (target == null) throw new IllegalArgumentException();
        double base = toBase();
        double converted = target.convertFromBaseUnit(base);
        return new Weight(converted, target);
    }

    public Weight add(Weight other) {
        if (other == null) throw new IllegalArgumentException();
        double sumBase = this.toBase() + other.toBase();
        double result = unit.convertFromBaseUnit(sumBase);
        return new Weight(result, this.unit);
    }

    public Weight add(Weight other, WeightUnit target) {
        if (other == null) throw new IllegalArgumentException();
        if (target == null) throw new IllegalArgumentException();
        double sumBase = this.toBase() + other.toBase();
        double result = target.convertFromBaseUnit(sumBase);
        return new Weight(result, target);
    }

    public static Weight add(Weight w1, Weight w2, WeightUnit target) {
        if (w1 == null || w2 == null) throw new IllegalArgumentException();
        if (target == null) throw new IllegalArgumentException();
        double sumBase = w1.toBase() + w2.toBase();
        double result = target.convertFromBaseUnit(sumBase);
        return new Weight(result, target);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Weight that = (Weight) obj;
        return Math.abs(this.toBase() - that.toBase()) < 1e-6;
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