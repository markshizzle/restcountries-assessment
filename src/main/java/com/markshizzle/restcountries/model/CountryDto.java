package com.markshizzle.restcountries.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object representing a country as returned by the REST Countries API.
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryDto {

    @JsonProperty("name")
    private Name name;

    @JsonProperty("region")
    private String region;

    @JsonProperty("population")
    private long population;

    @JsonProperty("area")
    private double area;

    @JsonProperty("borders")
    private String[] borders;

    @JsonProperty("cca3")
    private String cca3;

    /**
     * Calculates the population density based on population and area.
     *
     * @return population density, or 0 if area is 0 or less
     */
    public double getPopulationDensity() {
        return area > 0 ? population / area : 0;
    }

    /**
     * Inner class representing the nested 'name' object.
     */
    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Name {
        @JsonProperty("common")
        private String common;
    }
}