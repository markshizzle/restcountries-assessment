# REST Countries Assessment

This Java application uses the [REST Countries API](https://restcountries.com) to retrieve and analyze country data, with a focus on population density and geographic borders.

---

## Features

- Fetches real-time data from the REST Countries public API
- Calculates population density for all countries
- Displays the top 10 countries by population density
- Determines the Asian country with the most bordering countries outside Asia
- Includes logging via Java's built-in logger
- Unit tests for core logic (e.g., population density calculation)

---

## Technologies Used

- Java 17
- Maven
- Java HTTP Client (for API calls)
- Jackson (for JSON parsing)
- Lombok (for reducing DTO boilerplate)
- JUnit 5 (for unit testing)
- Java Util Logging (`java.util.logging`)

---

## Running the Application

1. Clone the repository
   ```bash
   git clone https://github.com/markshizzle/restcountries-assessment.git
   cd restcountries-assessment
   mvn compile exec:java -Dexec.mainClass="com.markshizzle.restcountries.RestCountriesApplication"

---

## Example Output

```plaintext
Top 10 population density:
Monaco                         density: 26337.04 people/km²
Macau                          density: 21539.50 people/km²
Singapore                      density: 8358.94 people/km²
...

Asian country with most non-Asian neighbours:
Turkey (2 non-Asian borders)

