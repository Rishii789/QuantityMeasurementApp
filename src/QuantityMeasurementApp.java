package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static class Length {
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

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Length that = (Length) obj;
            if (this.unit == null || that.unit == null) return false;
            return Double.compare(this.toBaseUnit(), that.toBaseUnit()) == 0;
        }
    }

    public static void main(String[] args) {
        Length l1 = new Length(1.0, Length.LengthUnit.YARDS);
        Length l2 = new Length(3.0, Length.LengthUnit.FEET);
        System.out.println(l1.equals(l2));
    }
}