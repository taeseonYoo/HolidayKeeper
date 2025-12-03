package com.planit.keeper.holiday.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.planit.keeper.holiday.application.HolidayResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HolidayMapper {
    private final ObjectMapper objectMapper;

    public List<HolidayResponse> toHolidayResponses(String json) throws JsonProcessingException {
        return objectMapper.readValue(json, new TypeReference<List<HolidayResponse>>() {});
    }
}
