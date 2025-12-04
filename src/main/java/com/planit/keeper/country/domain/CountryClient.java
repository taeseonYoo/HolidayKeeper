package com.planit.keeper.country.domain;

import com.planit.keeper.country.application.dto.CountryResponse;
import java.util.List;

public interface CountryClient {
    public List<CountryResponse> getAvailableCountries();
}
