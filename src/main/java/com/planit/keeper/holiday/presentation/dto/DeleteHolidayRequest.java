package com.planit.keeper.holiday.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class DeleteHolidayRequest {
    @NotBlank
    private final Integer year;
    @NotBlank
    private final String countryCode;

    public DeleteHolidayRequest(Integer year, String countryCode) {
        this.year = year;
        this.countryCode = countryCode;
    }
}
