package com.markshizzle.restcountries;

import com.markshizzle.restcountries.model.CountryDto;
import com.markshizzle.restcountries.service.CountryService;

import java.util.*;
import java.util.stream.Collectors;

public class RestCountriesApplication {
    public static void main(String[] args) throws Exception {
        CountryService service = new CountryService();
        List<CountryDto> countries = service.fetchAllCountries();

        System.out.println("Top 10 population density:");
        countries.stream()
                .filter(c -> c.area > 0)
                .sorted((a, b) -> Double.compare(
                        b.getPopulationDensity(),
                        a.getPopulationDensity()))
                .limit(10)
                .forEach(c -> System.out.printf(
                        "%-30s density: %.2f people/km²%n",
                        c.name != null ? c.name.common : "UNKNOWN",
                        c.getPopulationDensity()
                ));

        Map<String, CountryDto> countryByCca3 = countries.stream()
                .filter(c -> c.cca3 != null)
                .collect(Collectors.toMap(
                        c -> c.cca3,
                        c -> c,
                        (a, b) -> a
                ));

        CountryDto result = countries.stream()
                .filter(c -> "Asia".equalsIgnoreCase(c.region) && c.borders != null)
                .max(Comparator.comparingInt(c ->
                        (int) Arrays.stream(c.borders)
                                .map(countryByCca3::get)
                                .filter(n -> n != null && !"Asia".equalsIgnoreCase(n.region))
                                .count()
                ))
                .orElse(null);

        if (result != null) {
            long count = Arrays.stream(result.borders)
                    .map(countryByCca3::get)
                    .filter(n -> n != null && !"Asia".equalsIgnoreCase(n.region))
                    .count();

            System.out.printf(
                    "%nAsian country with most non-Asian neighbours:%n%s (%d non-Asian borders)%n",
                    result.name != null ? result.name.common : "UNKNOWN",
                    count
            );
        } else {
            System.out.println("No valid Asian country found.");
        }
    }
}
