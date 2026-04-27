package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.apps.quantitymeasurement.Length.LengthUnit;

public class QuantityMeasurementAppTest {

    @Test
    public void testEquality_FeetToFeet_SameValue() {
        assertTrue(new Length(1.0, LengthUnit.FEET).equals(new Length(1.0, LengthUnit.FEET)));
    }

    @Test
    public void testEquality_InchToInch_SameValue() {
        assertTrue(new Length(1.0, LengthUnit.INCHES).equals(new Length(1.0, LengthUnit.INCHES)));
    }

    @Test
    public void testEquality_FeetToInch_EquivalentValue() {
        assertTrue(new Length(1.0, LengthUnit.FEET).equals(new Length(12.0, LengthUnit.INCHES)));
    }

    @Test
    public void testEquality_InchToFeet_EquivalentValue() {
        assertTrue(new Length(12.0, LengthUnit.INCHES).equals(new Length(1.0, LengthUnit.FEET)));
    }

    @Test
    public void testEquality_YardToFeet_EquivalentValue() {
        assertTrue(new Length(1.0, LengthUnit.YARDS).equals(new Length(3.0, LengthUnit.FEET)));
    }

    @Test
    public void testEquality_CentimeterToInch_EquivalentValue() {
        assertTrue(new Length(2.54, LengthUnit.CENTIMETERS).equals(new Length(1.0, LengthUnit.INCHES)));
    }

    @Test
    public void testEquality_DifferentValue() {
        assertFalse(new Length(1.0, LengthUnit.FEET).equals(new Length(2.0, LengthUnit.FEET)));
    }

    @Test
    public void testEquality_NullComparison() {
        assertFalse(new Length(1.0, LengthUnit.FEET).equals(null));
    }

    @Test
    public void testEquality_SameReference() {
        Length l = new Length(1.0, LengthUnit.FEET);
        assertTrue(l.equals(l));
    }

    @Test
    public void testConversion_FeetToInches() {
        assertEquals(12.0, Length.convert(1.0, LengthUnit.FEET, LengthUnit.INCHES), 1e-6);
    }

    @Test
    public void testConversion_InchesToFeet() {
        assertEquals(2.0, Length.convert(24.0, LengthUnit.INCHES, LengthUnit.FEET), 1e-6);
    }

    @Test
    public void testConversion_YardsToInches() {
        assertEquals(36.0, Length.convert(1.0, LengthUnit.YARDS, LengthUnit.INCHES), 1e-6);
    }

    @Test
    public void testConversion_InchesToYards() {
        assertEquals(2.0, Length.convert(72.0, LengthUnit.INCHES, LengthUnit.YARDS), 1e-6);
    }

    @Test
    public void testConversion_CentimetersToInches() {
        assertEquals(1.0, Length.convert(2.54, LengthUnit.CENTIMETERS, LengthUnit.INCHES), 1e-6);
    }

    @Test
    public void testConversion_ZeroValue() {
        assertEquals(0.0, Length.convert(0.0, LengthUnit.FEET, LengthUnit.INCHES), 1e-6);
    }

    @Test
    public void testConversion_NegativeValue() {
        assertEquals(-12.0, Length.convert(-1.0, LengthUnit.FEET, LengthUnit.INCHES), 1e-6);
    }

    @Test
    public void testConversion_SameUnit() {
        assertEquals(5.0, Length.convert(5.0, LengthUnit.FEET, LengthUnit.FEET), 1e-6);
    }

    @Test
    public void testConversion_RoundTrip() {
        double v = 5.0;
        double result = Length.convert(
                Length.convert(v, LengthUnit.FEET, LengthUnit.INCHES),
                LengthUnit.INCHES,
                LengthUnit.FEET
        );
        assertEquals(v, result, 1e-6);
    }

    @Test
    public void testConversion_InvalidUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> Length.convert(1.0, null, LengthUnit.FEET));
    }

    @Test
    public void testConversion_NaN() {
        assertThrows(IllegalArgumentException.class,
                () -> Length.convert(Double.NaN, LengthUnit.FEET, LengthUnit.INCHES));
    }

    @Test
    public void testConversion_InfiniteValue() {
        assertThrows(IllegalArgumentException.class,
                () -> Length.convert(Double.POSITIVE_INFINITY, LengthUnit.FEET, LengthUnit.INCHES));
    }

    @Test
    public void testAddition_SameUnit_FeetPlusFeet() {
        Length result = new Length(1.0, LengthUnit.FEET)
                .add(new Length(2.0, LengthUnit.FEET));
        assertEquals(new Length(3.0, LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_CrossUnit_FeetPlusInches() {
        Length result = new Length(1.0, LengthUnit.FEET)
                .add(new Length(12.0, LengthUnit.INCHES));
        assertEquals(new Length(2.0, LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Feet() {
        Length result = Length.add(
                new Length(1.0, LengthUnit.FEET),
                new Length(12.0, LengthUnit.INCHES),
                LengthUnit.FEET
        );
        assertEquals(new Length(2.0, LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Inches() {
        Length result = Length.add(
                new Length(1.0, LengthUnit.FEET),
                new Length(12.0, LengthUnit.INCHES),
                LengthUnit.INCHES
        );
        assertEquals(new Length(24.0, LengthUnit.INCHES), result);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Yards() {
        Length result = Length.add(
                new Length(1.0, LengthUnit.FEET),
                new Length(12.0, LengthUnit.INCHES),
                LengthUnit.YARDS
        );
        assertEquals(0.6666667, result.convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).convertTo(LengthUnit.YARDS).value, 1e-5);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Centimeters() {
        Length result = Length.add(
                new Length(1.0, LengthUnit.INCHES),
                new Length(1.0, LengthUnit.INCHES),
                LengthUnit.CENTIMETERS
        );
        assertEquals(5.08, result.convertTo(LengthUnit.CENTIMETERS).value, 1e-2);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Commutativity() {
        Length a = new Length(1.0, LengthUnit.FEET);
        Length b = new Length(12.0, LengthUnit.INCHES);
        Length r1 = Length.add(a, b, LengthUnit.YARDS);
        Length r2 = Length.add(b, a, LengthUnit.YARDS);
        assertEquals(r1, r2);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_WithZero() {
        Length result = Length.add(
                new Length(5.0, LengthUnit.FEET),
                new Length(0.0, LengthUnit.INCHES),
                LengthUnit.YARDS
        );
        assertEquals(1.6666667, result.convertTo(LengthUnit.YARDS).value, 1e-5);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_NegativeValues() {
        Length result = Length.add(
                new Length(5.0, LengthUnit.FEET),
                new Length(-2.0, LengthUnit.FEET),
                LengthUnit.INCHES
        );
        assertEquals(36.0, result.convertTo(LengthUnit.INCHES).value, 1e-6);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_NullTargetUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> Length.add(
                        new Length(1.0, LengthUnit.FEET),
                        new Length(12.0, LengthUnit.INCHES),
                        null
                ));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_LargeToSmallScale() {
        Length result = Length.add(
                new Length(1000.0, LengthUnit.FEET),
                new Length(500.0, LengthUnit.FEET),
                LengthUnit.INCHES
        );
        assertEquals(18000.0, result.convertTo(LengthUnit.INCHES).value, 1e-6);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_SmallToLargeScale() {
        Length result = Length.add(
                new Length(12.0, LengthUnit.INCHES),
                new Length(12.0, LengthUnit.INCHES),
                LengthUnit.YARDS
        );
        assertEquals(0.6666667, result.convertTo(LengthUnit.YARDS).value, 1e-5);
    }
}