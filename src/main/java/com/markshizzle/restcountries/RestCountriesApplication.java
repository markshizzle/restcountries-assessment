// File: RestCountriesApplication.java
package com.markshizzle.restcountries;

import com.markshizzle.restcountries.model.CountryDto;
import com.markshizzle.restcountries.service.CountryService;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Main application class for retrieving and analyzing country data
 * using the REST Countries API.
 *
 * Functionality:
 * - Print the top 10 countries by population density.
 * - Identify the Asian country with the most non-Asian bordering countries.
 */
public class RestCountriesApplication {

    private static final Logger logger = Logger.getLogger(RestCountriesApplication.class.getName());

    public static void main(String[] args) {
        try {
            List<CountryDto> countries = fetchCountries();
            printTop10ByDensity(countries);
            printAsianCountryWithMostNonAsianNeighbors(countries);
        } catch (IOException | InterruptedException e) {
            logger.severe("Fatal error: " + e.getMessage());
        }
    }

    /**
     * Fetches countries from the REST Countries API via the service layer.
     *
     * @return List of CountryDto
     * @throws IOException if an I/O error occurs
     * @throws InterruptedException if the HTTP request is interrupted
     */
    private static List<CountryDto> fetchCountries() throws IOException, InterruptedException {
        CountryService service = new CountryService();
        List<CountryDto> countries = service.fetchAllCountries();
        logger.info("Fetched " + countries.size() + " countries from API.");
        return countries;
    }

    /**
     * Prints the top 10 countries with the highest population density.
     *
     * @param countries list of countries
     */
    private static void printTop10ByDensity(List<CountryDto> countries) {
        System.out.println("Top 10 population density:");
        countries.stream()
                .filter(c -> c.getArea() > 0)
                .sorted(Comparator.comparingDouble(CountryDto::getPopulationDensity).reversed())
                .limit(10)
                .forEach(c -> System.out.printf(
                        "%-30s density: %.2f people/km²%n",
                        c.getName() != null ? c.getName().getCommon() : "UNKNOWN",
                        c.getPopulationDensity()
                ));
    }

    /**
     * Identifies and prints the Asian country that borders the most countries from other regions.
     *
     * @param countries list of countries
     */
    private static void printAsianCountryWithMostNonAsianNeighbors(List<CountryDto> countries) {
        Map<String, CountryDto> countryByCca3 = countries.stream()
                .filter(c -> c.getCca3() != null)
                .collect(Collectors.toMap(
                        CountryDto::getCca3,
                        c -> c,
                        (a, b) -> a
                ));

        Optional<CountryDto> candidate = countries.stream()
                .filter(c -> "Asia".equalsIgnoreCase(c.getRegion()) && c.getBorders() != null)
                .max(Comparator.comparingInt(c ->
                        (int) Arrays.stream(c.getBorders())
                                .map(countryByCca3::get)
                                .filter(n -> n != null && !"Asia".equalsIgnoreCase(n.getRegion()))
                                .count()
                ));

        if (candidate.isEmpty()) {
            logger.warning("No valid Asian country found with non-Asian borders.");
            return;
        }

        CountryDto result = candidate.get();
        long count = Arrays.stream(result.getBorders())
                .map(countryByCca3::get)
                .filter(n -> n != null && !"Asia".equalsIgnoreCase(n.getRegion()))
                .count();

        System.out.printf(
                "%nAsian country with most non-Asian neighbours:%n%s (%d non-Asian borders)%n",
                result.getName() != null ? result.getName().getCommon() : "UNKNOWN",
                count
        );
    }
}
