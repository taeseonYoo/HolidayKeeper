package com.planit.keeper.holiday.presentation.dto;

import lombok.Getter;

@Getter
public class DeleteHolidayRequest {
    private final Integer year;
    private final String countryCode;

    public DeleteHolidayRequest(Integer year, String countryCode) {
        this.year = year;
        this.countryCode = countryCode;
    }
}
