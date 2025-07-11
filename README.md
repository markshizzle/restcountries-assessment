# REST Countries Assessment

This Java project uses the REST Countries API to fetch country information and perform simple analyses.

## Features

- Retrieves data on all countries via a public REST API
- Calculates population density (population / area)
- Displays the top 10 countries with the highest population density
- Determines the Asian country with the most neighboring countries outside Asia
- Includes unit tests for the logic related to population density

## Technologies Used

- Java 17
- Maven
- HttpClient (for API requests)
- Jackson (for JSON parsing)
- JUnit 5 (for unit testing)

## Running

To run the project:

```bash
mvn compile exec:java -Dexec.mainClass="com.markshizzle.restcountries.RestCountriesApplication"
