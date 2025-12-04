package com.planit.keeper.country.infra;

import com.planit.keeper.country.application.dto.CountryResponse;
import com.planit.keeper.country.domain.CountryClient;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class NagerCountryApiClient implements CountryClient {
    @Value("${external.nager.base-url}")
    private String baseUrl;
    private final RestClient restClient;

    public NagerCountryApiClient(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl(baseUrl)
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
