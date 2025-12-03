package com.planit.keeper.holiday.presentation.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HolidayResponse {
    @Schema(description = "공휴일 날짜",example = "2025-12-03")
    private LocalDate date;
    @Schema(description = "공휴일 현지명",example = "새해")
    private String localName;
    @Schema(description = "공휴일 영문명",example = "New Year's Day")
    private String name;
    @Schema(description = "지역 코드",example = "KR")
    private String countryCode;
    @Schema(description = "매년 같은 날짜 여부(현재 사용하지 않는 정보)",example = "false")
    private boolean fixed;
    @Schema(description = "전역 공휴일 여부",example = "true")
    private boolean global;
    @Schema(description = "주별 공휴일 코드 목록",example = "[\"US-TX\",\"US-VA\"]")
    private List<String> counties = new ArrayList<>();
    @Schema(description = "공휴일 시행 시작 연도")
    private Integer launchYear;
    @Schema(description ="공휴일 유형 목록",example = "[\"Public\", \"Optional\"]")
    private List<String> types = new ArrayList<>();

    public HolidayResponse(LocalDate date, String localName, String name, String countryCode, boolean fixed,
                           boolean global,
                           List<String> counties, Integer launchYear, List<String> types) {
        this.date = date;
        this.localName = localName;
        this.name = name;
        this.countryCode = countryCode;
        this.fixed = fixed;
        this.global = global;
        this.counties = counties;
        this.launchYear = launchYear;
        this.types = types;
    }
}
