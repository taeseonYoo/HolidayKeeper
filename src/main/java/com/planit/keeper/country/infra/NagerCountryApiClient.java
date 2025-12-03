package com.planit.keeper.country.infra;

import com.planit.keeper.country.application.CountryResponse;
import com.planit.keeper.country.domain.CountryClient;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class NagerCountryApiClient implements CountryClient {
    private final RestClient restClient;

    public NagerCountryApiClient(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("https://date.nager.at/api/v3")
                .build();
    }

    @Override
    public List<CountryResponse> getAvailableCountries() {
        return restClient.get()
                .uri("/AvailableCountries")
                .retrieve()
                .body(new ParameterizedTypeReference<List<CountryResponse>>() {
                });
    }
}
