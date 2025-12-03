package com.planit.holiday.country.infra;

import com.planit.holiday.country.application.CountryResponse;
import com.planit.holiday.country.domain.HolidayClient;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class NagerHolidayApiClient implements HolidayClient {
    private final RestClient restClient;

    public NagerHolidayApiClient(RestClient.Builder builder) {
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
