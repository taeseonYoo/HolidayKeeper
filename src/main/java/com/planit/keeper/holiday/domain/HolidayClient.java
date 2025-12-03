package com.planit.keeper.holiday.domain;

import java.util.List;

public interface HolidayClient {
    public String getPublicHolidays(String countryCode, int year);
}
