package com.planit.keeper.holiday.domain;

import com.planit.keeper.global.JsonListConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(
        indexes = {
                @Index(name = "idx_holiday_year",columnList = "holiday_year"),
                @Index(name = "idx_country_code",columnList = "country_code"),
                @Index(name = "idx_holiday_year_country_code",columnList = "holiday_year,country_code")
        }
)
public class Holiday {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private LocalDate date;
    private String localName;
    private String name;
    private String countryCode;
    private boolean fixed;
    private boolean global;
    @Convert(converter = JsonListConverter.class)
    @Lob
    private List<String> counties = new ArrayList<>();
    private Integer launchYear;
    @Convert(converter = JsonListConverter.class)
    @Lob
    private List<String> types = new ArrayList<>();
    private int holidayYear;

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

        this.holidayYear = date.getYear();
    }
}
