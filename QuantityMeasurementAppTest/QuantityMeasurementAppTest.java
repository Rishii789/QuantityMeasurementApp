package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPS = 1e-6;

    @Test
    public void testLengthEquality_SameUnit() {
        assertTrue(new Length(1.0, LengthUnit.FEET)
                .equals(new Length(1.0, LengthUnit.FEET)));
    }

    @Test
    public void testLengthEquality_CrossUnit() {
        assertTrue(new Length(1.0, LengthUnit.FEET)
                .equals(new Length(12.0, LengthUnit.INCHES)));
    }

    @Test
    public void testLengthEquality_DifferentValue() {
        assertFalse(new Length(1.0, LengthUnit.FEET)
                .equals(new Length(2.0, LengthUnit.FEET)));
    }

    @Test
    public void testLengthEquality_Null() {
        assertFalse(new Length(1.0, LengthUnit.FEET).equals(null));
    }

    @Test
    public void testLengthEquality_SameReference() {
        Length l = new Length(1.0, LengthUnit.FEET);
        assertTrue(l.equals(l));
    }

    @Test
    public void testLengthConversion_FeetToInches() {
        assertEquals(12.0,
                Length.convert(1.0, LengthUnit.FEET, LengthUnit.INCHES), EPS);
    }

    @Test
    public void testLengthConversion_YardToFeet() {
        assertEquals(3.0,
                Length.convert(1.0, LengthUnit.YARDS, LengthUnit.FEET), EPS);
    }

    @Test
    public void testLengthConversion_CmToInches() {
        assertEquals(1.0,
                Length.convert(2.54, LengthUnit.CENTIMETERS, LengthUnit.INCHES), EPS);
    }

    @Test
    public void testLengthConversion_SameUnit() {
        assertEquals(5.0,
                Length.convert(5.0, LengthUnit.FEET, LengthUnit.FEET), EPS);
    }

    @Test
    public void testLengthConversion_Zero() {
        assertEquals(0.0,
                Length.convert(0.0, LengthUnit.FEET, LengthUnit.INCHES), EPS);
    }

    @Test
    public void testLengthConversion_Negative() {
        assertEquals(-12.0,
                Length.convert(-1.0, LengthUnit.FEET, LengthUnit.INCHES), EPS);
    }

    @Test
    public void testLengthAddition_SameUnit() {
        Length result = new Length(1.0, LengthUnit.FEET)
                .add(new Length(2.0, LengthUnit.FEET));
        assertEquals(new Length(3.0, LengthUnit.FEET), result);
    }

    @Test
    public void testLengthAddition_CrossUnit() {
        Length result = new Length(1.0, LengthUnit.FEET)
                .add(new Length(12.0, LengthUnit.INCHES));
        assertEquals(new Length(2.0, LengthUnit.FEET), result);
    }

    @Test
    public void testLengthAddition_TargetUnit() {
        Length result = Length.add(
                new Length(1.0, LengthUnit.FEET),
                new Length(12.0, LengthUnit.INCHES),
                LengthUnit.INCHES
        );
        assertEquals(new Length(24.0, LengthUnit.INCHES), result);
    }

    @Test
    public void testLengthAddition_Zero() {
        Length result = new Length(5.0, LengthUnit.FEET)
                .add(new Length(0.0, LengthUnit.INCHES));
        assertEquals(new Length(5.0, LengthUnit.FEET), result);
    }

    @Test
    public void testLengthAddition_Negative() {
        Length result = new Length(5.0, LengthUnit.FEET)
                .add(new Length(-2.0, LengthUnit.FEET));
        assertEquals(new Length(3.0, LengthUnit.FEET), result);
    }

    @Test
    public void testWeightEquality_SameUnit() {
        assertTrue(new Weight(1.0, WeightUnit.KILOGRAM)
                .equals(new Weight(1.0, WeightUnit.KILOGRAM)));
    }

    @Test
    public void testWeightEquality_KgToGram() {
        assertTrue(new Weight(1.0, WeightUnit.KILOGRAM)
                .equals(new Weight(1000.0, WeightUnit.GRAM)));
    }

    @Test
    public void testWeightEquality_KgToPound() {
        assertTrue(new Weight(1.0, WeightUnit.KILOGRAM)
                .equals(new Weight(2.20462, WeightUnit.POUND)));
    }

    @Test
    public void testWeightEquality_DifferentValue() {
        assertFalse(new Weight(1.0, WeightUnit.KILOGRAM)
                .equals(new Weight(2.0, WeightUnit.KILOGRAM)));
    }

    @Test
    public void testWeightEquality_Null() {
        assertFalse(new Weight(1.0, WeightUnit.KILOGRAM).equals(null));
    }

    @Test
    public void testWeightConversion_KgToGram() {
        Weight result = new Weight(1.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM);
        assertEquals(1000.0, result.getValue(), EPS);
    }

    @Test
    public void testWeightConversion_PoundToKg() {
        Weight result = new Weight(2.20462, WeightUnit.POUND)
                .convertTo(WeightUnit.KILOGRAM);
        assertEquals(1.0, result.getValue(), 1e-3);
    }

    @Test
    public void testWeightConversion_SameUnit() {
        Weight result = new Weight(5.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.KILOGRAM);
        assertEquals(5.0, result.getValue(), EPS);
    }

    @Test
    public void testWeightAddition_SameUnit() {
        Weight result = new Weight(1.0, WeightUnit.KILOGRAM)
                .add(new Weight(2.0, WeightUnit.KILOGRAM));
        assertEquals(new Weight(3.0, WeightUnit.KILOGRAM), result);
    }

    @Test
    public void testWeightAddition_CrossUnit() {
        Weight result = new Weight(1.0, WeightUnit.KILOGRAM)
                .add(new Weight(1000.0, WeightUnit.GRAM));
        assertEquals(new Weight(2.0, WeightUnit.KILOGRAM), result);
    }

    @Test
    public void testWeightAddition_TargetUnit() {
        Weight result = Weight.add(
                new Weight(1.0, WeightUnit.KILOGRAM),
                new Weight(1000.0, WeightUnit.GRAM),
                WeightUnit.GRAM
        );
        assertEquals(new Weight(2000.0, WeightUnit.GRAM), result);
    }

    @Test
    public void testWeightAddition_Zero() {
        Weight result = new Weight(5.0, WeightUnit.KILOGRAM)
                .add(new Weight(0.0, WeightUnit.GRAM));
        assertEquals(new Weight(5.0, WeightUnit.KILOGRAM), result);
    }

    @Test
    public void testWeightAddition_Negative() {
        Weight result = new Weight(5.0, WeightUnit.KILOGRAM)
                .add(new Weight(-2000.0, WeightUnit.GRAM));
        assertEquals(new Weight(3.0, WeightUnit.KILOGRAM), result);
    }

    @Test
    public void testWeightVsLength_Incompatible() {
        assertFalse(new Weight(1.0, WeightUnit.KILOGRAM)
                .equals(new Length(1.0, LengthUnit.FEET)));
    }

    @Test
    public void testWeightInvalidInput_NaN() {
        assertThrows(IllegalArgumentException.class,
                () -> new Weight(Double.NaN, WeightUnit.KILOGRAM));
    }

    @Test
    public void testWeightInvalidInput_NullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Weight(1.0, null));
    }

    @Test
    public void testWeightZeroEquality() {
        assertTrue(new Weight(0.0, WeightUnit.KILOGRAM)
                .equals(new Weight(0.0, WeightUnit.GRAM)));
    }

    @Test
    public void testWeightNegativeEquality() {
        assertTrue(new Weight(-1.0, WeightUnit.KILOGRAM)
                .equals(new Weight(-1000.0, WeightUnit.GRAM)));
    }

    @Test
    public void testWeightRoundTrip() {
        Weight w = new Weight(1.5, WeightUnit.KILOGRAM);
        Weight result = w.convertTo(WeightUnit.GRAM)
                .convertTo(WeightUnit.KILOGRAM);
        assertEquals(w.getValue(), result.getValue(), EPS);
    }

    @Test
    public void testWeightLargeValues() {
        Weight result = new Weight(1e6, WeightUnit.KILOGRAM)
                .add(new Weight(1e6, WeightUnit.KILOGRAM));
        assertEquals(new Weight(2e6, WeightUnit.KILOGRAM), result);
    }
}