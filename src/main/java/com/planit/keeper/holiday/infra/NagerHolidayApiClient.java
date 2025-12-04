package com.planit.keeper.holiday.infra;

import com.planit.keeper.holiday.application.HolidayMapper;
import com.planit.keeper.holiday.domain.HolidayClient;
import com.planit.keeper.holiday.presentation.dto.HolidayResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class NagerHolidayApiClient implements HolidayClient {
    @Value("${external.nager.base-url}")
    private String baseUrl;
    private final RestClient restClient;
    private final HolidayMapper holidayMapper;

    public NagerHolidayApiClient(RestClient.Builder builder, HolidayMapper holidayMapper) {
        this.restClient = builder
                .baseUrl(baseUrl)
                .build();
        this.holidayMapper = holidayMapper;
    }

    @Override
    public List<HolidayResponse> getPublicHolidays(String countryCode, int year) {
        String body = restClient.get()
                .uri("/PublicHolidays" + "/" + year + "/" + countryCode)
                .retrieve()
                .body(String.class);
        return holidayMapper.toHolidayResponses(body);
    }
}
