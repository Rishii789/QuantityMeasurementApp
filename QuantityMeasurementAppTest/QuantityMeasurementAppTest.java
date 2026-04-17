package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.apps.quantitymeasurement.QuantityMeasurementApp.Length;
import com.apps.quantitymeasurement.QuantityMeasurementApp.Length.LengthUnit;

public class QuantityMeasurementAppTest {

    @Test
    public void testEquality_YardToYard_SameValue() {
        Length l1 = new Length(1.0, LengthUnit.YARDS);
        Length l2 = new Length(1.0, LengthUnit.YARDS);
        assertTrue(l1.equals(l2));
    }

    @Test
    public void testEquality_YardToYard_DifferentValue() {
        Length l1 = new Length(1.0, LengthUnit.YARDS);
        Length l2 = new Length(2.0, LengthUnit.YARDS);
        assertFalse(l1.equals(l2));
    }

    @Test
    public void testEquality_YardToFeet_EquivalentValue() {
        Length l1 = new Length(1.0, LengthUnit.YARDS);
        Length l2 = new Length(3.0, LengthUnit.FEET);
        assertTrue(l1.equals(l2));
    }

    @Test
    public void testEquality_FeetToYard_EquivalentValue() {
        Length l1 = new Length(3.0, LengthUnit.FEET);
        Length l2 = new Length(1.0, LengthUnit.YARDS);
        assertTrue(l1.equals(l2));
    }

    @Test
    public void testEquality_YardToInches_EquivalentValue() {
        Length l1 = new Length(1.0, LengthUnit.YARDS);
        Length l2 = new Length(36.0, LengthUnit.INCHES);
        assertTrue(l1.equals(l2));
    }

    @Test
    public void testEquality_InchesToYard_EquivalentValue() {
        Length l1 = new Length(36.0, LengthUnit.INCHES);
        Length l2 = new Length(1.0, LengthUnit.YARDS);
        assertTrue(l1.equals(l2));
    }

    @Test
    public void testEquality_YardToFeet_NonEquivalentValue() {
        Length l1 = new Length(1.0, LengthUnit.YARDS);
        Length l2 = new Length(2.0, LengthUnit.FEET);
        assertFalse(l1.equals(l2));
    }

    @Test
    public void testEquality_CentimetersToInches_EquivalentValue() {
        Length l1 = new Length(1.0, LengthUnit.CENTIMETERS);
        Length l2 = new Length(0.393701, LengthUnit.INCHES);
        assertTrue(l1.equals(l2));
    }

    @Test
    public void testEquality_CentimetersToFeet_NonEquivalentValue() {
        Length l1 = new Length(1.0, LengthUnit.CENTIMETERS);
        Length l2 = new Length(1.0, LengthUnit.FEET);
        assertFalse(l1.equals(l2));
    }

    @Test
    public void testEquality_MultiUnit_TransitiveProperty() {
        Length a = new Length(1.0, LengthUnit.YARDS);
        Length b = new Length(3.0, LengthUnit.FEET);
        Length c = new Length(36.0, LengthUnit.INCHES);
        assertTrue(a.equals(b));
        assertTrue(b.equals(c));
        assertTrue(a.equals(c));
    }

    @Test
    public void testEquality_YardWithNullUnit() {
        Length l1 = new Length(1.0, null);
        Length l2 = new Length(1.0, LengthUnit.YARDS);
        assertFalse(l1.equals(l2));
    }

    @Test
    public void testEquality_YardSameReference() {
        Length l1 = new Length(1.0, LengthUnit.YARDS);
        assertTrue(l1.equals(l1));
    }

    @Test
    public void testEquality_YardNullComparison() {
        Length l1 = new Length(1.0, LengthUnit.YARDS);
        assertFalse(l1.equals(null));
    }

    @Test
    public void testEquality_CentimetersWithNullUnit() {
        Length l1 = new Length(1.0, null);
        Length l2 = new Length(1.0, LengthUnit.CENTIMETERS);
        assertFalse(l1.equals(l2));
    }

    @Test
    public void testEquality_CentimetersSameReference() {
        Length l1 = new Length(1.0, LengthUnit.CENTIMETERS);
        assertTrue(l1.equals(l1));
    }

    @Test
    public void testEquality_CentimetersNullComparison() {
        Length l1 = new Length(1.0, LengthUnit.CENTIMETERS);
        assertFalse(l1.equals(null));
    }

    @Test
    public void testEquality_AllUnits_ComplexScenario() {
        Length a = new Length(2.0, LengthUnit.YARDS);
        Length b = new Length(6.0, LengthUnit.FEET);
        Length c = new Length(72.0, LengthUnit.INCHES);
        assertTrue(a.equals(b));
        assertTrue(b.equals(c));
        assertTrue(a.equals(c));
    }
}