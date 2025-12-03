package com.planit.keeper.holiday.domain;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Holiday {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate date;
    private String localName;
    private String name;
    private String countryCode;
    private boolean fixed;
    private boolean global;
    @ElementCollection
    private List<String> counties = new ArrayList<>();
    private Integer launchYear;
    @ElementCollection
    private List<String> types = new ArrayList<>();
    //연도 저장 필드 for indexing
    @Column(name = "holiday_year")
    private int year;

    public Holiday(LocalDate date, String localName, String name, String countryCode, boolean fixed,
                   boolean global, List<String> counties, Integer launchYear, List<String> types) {
        this.date = date;
        this.localName = localName;
        this.name = name;
        this.countryCode = countryCode;
        this.fixed = fixed;
        this.global = global;
        this.counties = counties;
        this.launchYear = launchYear;
        this.types = types;

        this.year = date.getYear();
    }
}
