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
    public void testConversion_FeetToYards() {
        assertEquals(2.0, Length.convert(6.0, LengthUnit.FEET, LengthUnit.YARDS), 1e-6);
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
    public void testConversion_LargeValue() {
        assertEquals(1200000.0,
                Length.convert(100000.0, LengthUnit.FEET, LengthUnit.INCHES), 1e-6);
    }

    @Test
    public void testConversion_SmallValue() {
        assertEquals(0.12,
                Length.convert(0.01, LengthUnit.FEET, LengthUnit.INCHES), 1e-6);
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
    public void testAddition_SameUnit_InchPlusInch() {
        Length result = new Length(6.0, LengthUnit.INCHES)
                .add(new Length(6.0, LengthUnit.INCHES));
        assertEquals(new Length(12.0, LengthUnit.INCHES), result);
    }

    @Test
    public void testAddition_CrossUnit_FeetPlusInches() {
        Length result = new Length(1.0, LengthUnit.FEET)
                .add(new Length(12.0, LengthUnit.INCHES));
        assertEquals(new Length(2.0, LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_CrossUnit_InchPlusFeet() {
        Length result = new Length(12.0, LengthUnit.INCHES)
                .add(new Length(1.0, LengthUnit.FEET));
        assertEquals(new Length(24.0, LengthUnit.INCHES), result);
    }

    @Test
    public void testAddition_CrossUnit_YardPlusFeet() {
        Length result = new Length(1.0, LengthUnit.YARDS)
                .add(new Length(3.0, LengthUnit.FEET));
        assertEquals(new Length(2.0, LengthUnit.YARDS), result);
    }

    @Test
    public void testAddition_CrossUnit_CentimeterPlusInch() {
        Length result = new Length(2.54, LengthUnit.CENTIMETERS)
                .add(new Length(1.0, LengthUnit.INCHES));
        assertEquals(new Length(5.08, LengthUnit.CENTIMETERS), result);
    }

    @Test
    public void testAddition_Commutativity() {
        Length a = new Length(1.0, LengthUnit.FEET);
        Length b = new Length(12.0, LengthUnit.INCHES);
        assertEquals(
                a.add(b),
                b.add(a).convertTo(LengthUnit.FEET)
        );
    }

    @Test
    public void testAddition_WithZero() {
        Length result = new Length(5.0, LengthUnit.FEET)
                .add(new Length(0.0, LengthUnit.INCHES));
        assertEquals(new Length(5.0, LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_NegativeValues() {
        Length result = new Length(5.0, LengthUnit.FEET)
                .add(new Length(-2.0, LengthUnit.FEET));
        assertEquals(new Length(3.0, LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_NullSecondOperand() {
        assertThrows(IllegalArgumentException.class,
                () -> new Length(1.0, LengthUnit.FEET).add(null));
    }

    @Test
    public void testAddition_LargeValues() {
        Length result = new Length(1e6, LengthUnit.FEET)
                .add(new Length(1e6, LengthUnit.FEET));
        assertEquals(new Length(2e6, LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_SmallValues() {
        Length result = new Length(0.001, LengthUnit.FEET)
                .add(new Length(0.002, LengthUnit.FEET));
        assertEquals(new Length(0.003, LengthUnit.FEET), result);
    }
}