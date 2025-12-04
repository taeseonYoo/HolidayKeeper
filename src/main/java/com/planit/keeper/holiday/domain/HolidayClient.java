package com.planit.keeper.holiday.domain;

import com.planit.keeper.holiday.presentation.dto.HolidayResponse;
import java.util.List;

public interface HolidayClient {
    public List<HolidayResponse> getPublicHolidays(String countryCode, int year);
}
