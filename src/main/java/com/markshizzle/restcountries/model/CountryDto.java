package com.markshizzle.restcountries.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryDto {

    @JsonProperty("name")
    public Name name;

    @JsonProperty("region")
    public String region;

    @JsonProperty("population")
    public long population;

    @JsonProperty("area")
    public double area;

    @JsonProperty("borders")
    public String[] borders;

    @JsonProperty("cca3")
    public String cca3;

    public CountryDto() {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Name {
        @JsonProperty("common")
        public String common;

        public Name() {}
    }

    public double getPopulationDensity() {
        return area > 0 ? population / area : 0;
    }
}
