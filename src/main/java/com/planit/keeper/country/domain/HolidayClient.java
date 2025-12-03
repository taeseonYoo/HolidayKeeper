package com.planit.keeper.country.domain;

import com.planit.keeper.country.application.CountryResponse;
import java.util.List;

public interface HolidayClient {
    public List<CountryResponse> getAvailableCountries();
}
