package com.markshizzle.restcountries.model;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CountryDtoTest {

    @Test
    public void testPopulationDensityCalculation() {
        CountryDto c = new CountryDto();
        c.population = 1000000;
        c.area = 500.0;

        assertEquals(2000.0, c.getPopulationDensity(), 0.001);
    }

    @Test
    public void testPopulationDensityWhenAreaIsZero() {
        CountryDto c = new CountryDto();
        c.population = 1000000;
        c.area = 0;

        assertEquals(0.0, c.getPopulationDensity());
    }
}
