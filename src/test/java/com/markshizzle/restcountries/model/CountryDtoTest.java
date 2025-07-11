package com.markshizzle.restcountries.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for CountryDto methods.
 */
class CountryDtoTest {

    @Test
    void testGetPopulationDensity_valid() {
        CountryDto dto = new CountryDto();
        dto.setArea(100.0);
        dto.setPopulation(1000);

        assertEquals(10.0, dto.getPopulationDensity(), 0.01);
    }

    @Test
    void testGetPopulationDensity_zeroArea() {
        CountryDto dto = new CountryDto();
        dto.setArea(0.0);
        dto.setPopulation(1000);

        assertEquals(0.0, dto.getPopulationDensity(), 0.01);
    }

    @Test
    void testGetPopulationDensity_negativeArea() {
        CountryDto dto = new CountryDto();
        dto.setArea(-5.0);
        dto.setPopulation(1000);

        assertEquals(0.0, dto.getPopulationDensity(), 0.01);
    }
}
