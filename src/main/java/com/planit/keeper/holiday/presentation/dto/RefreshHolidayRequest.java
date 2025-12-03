package com.planit.keeper.holiday.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RefreshHolidayRequest {
    @Schema(description = "조회할 연도",example = "2025")
    @NotBlank
    private final Integer year;
    @Schema(description = "조회할 국가 코드",example = "KR")
    @NotBlank
    private final String countryCode;

    public RefreshHolidayRequest(Integer year, String countryCode) {
        this.year = year;
        this.countryCode = countryCode;
    }
}
