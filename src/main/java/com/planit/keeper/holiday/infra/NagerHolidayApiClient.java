package com.planit.keeper.holiday.infra;

import com.planit.keeper.holiday.domain.HolidayClient;
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
    public String getPublicHolidays(String countryCode, int year){
        return restClient.get()
                .uri("/PublicHolidays" + "/" + year + "/" + countryCode)
                .retrieve()
                .body(String.class);
    }
}
