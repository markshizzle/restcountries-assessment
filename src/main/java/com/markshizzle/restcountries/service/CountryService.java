package com.markshizzle.restcountries.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.markshizzle.restcountries.model.CountryDto;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.logging.Logger;

/**
 * Service class responsible for fetching country data from
 * the REST Countries API and converting it into CountryDto objects.
 */
public class CountryService {

    private static final Logger logger = Logger.getLogger(CountryService.class.getName());
    private static final String API_URL = "https://restcountries.com/v3.1/all?fields=name,region,area,population,borders,cca3";
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Fetches all countries from the public API and returns them
     * as a list of CountryDto.
     *
     * @return List of CountryDto
     * @throws IOException if the request fails or JSON cannot be parsed
     * @throws InterruptedException if the request is interrupted
     */
    public List<CountryDto> fetchAllCountries() throws IOException, InterruptedException {
        logger.info("Sending request to REST Countries API: " + API_URL);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .GET()
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            logger.severe("API returned error: " + response.statusCode());
            throw new IOException("Failed to fetch data. Status code: " + response.statusCode());
        }

        List<CountryDto> countries = mapper.readValue(response.body(), new TypeReference<>() {});
        logger.info("Successfully parsed " + countries.size() + " countries.");
        return countries;
    }
}