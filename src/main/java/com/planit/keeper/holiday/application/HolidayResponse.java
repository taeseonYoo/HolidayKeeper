package com.planit.keeper.holiday.application;


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
}
