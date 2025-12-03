package com.planit.keeper.holiday.presentation.dto;

import lombok.Getter;

@Getter
public class RefreshHolidayRequest {
    private final Integer year;
    private final String countryCode;

    public RefreshHolidayRequest(Integer year, String countryCode) {
        this.year = year;
        this.countryCode = countryCode;
    }
}
