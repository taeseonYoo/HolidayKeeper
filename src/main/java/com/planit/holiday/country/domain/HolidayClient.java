package com.planit.holiday.country.domain;

import com.planit.holiday.country.application.CountryResponse;
import java.util.List;

public interface HolidayClient {
    public List<CountryResponse> getAvailableCountries();
}
