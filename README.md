# REST Countries Assessment

Dit Java-project maakt gebruik van de REST Countries API om landeninformatie op te halen en hierop eenvoudige analyses uit te voeren.

## Functionaliteiten

- Haalt gegevens op over alle landen via een publieke REST API
- Berekent bevolkingsdichtheid (population / area)
- Toont de top 10 landen met de hoogste bevolkingsdichtheid
- Bepaalt het Aziatische land dat het meeste aantal buurlanden heeft buiten Azië
- Bevat unit tests voor de logica rondom bevolkingsdichtheid

## Gebruikte technologieën

- Java 17
- Maven
- HttpClient (voor API-aanroep)
- Jackson (voor JSON-parsing)
- JUnit 5 (voor unit testing)

## Uitvoeren

Voor het uitvoeren van het project:

```bash
mvn compile exec:java -Dexec.mainClass="com.markshizzle.restcountries.RestCountriesApplication"
