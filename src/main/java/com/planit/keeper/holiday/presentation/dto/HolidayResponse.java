package com.planit.keeper.holiday.presentation.dto;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HolidayResponse {
    private LocalDate date;
    private String localName;
    private String name;
    private String countryCode;
    private boolean fixed;
    private boolean global;
    private List<String> counties = new ArrayList<>();
    private Integer launchYear;
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
