package guru.springframework.spring_ai_intro.models;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record GetCapitalWithInfoResponse(@JsonPropertyDescription("This is the city name") String city, @JsonPropertyDescription("This is the population of the city") String population, @JsonPropertyDescription("This is where the city is.") String region, @JsonPropertyDescription("This is the language spoken in the city.") String language, @JsonPropertyDescription("This is the currency used in the city.") String currency) {
}
