package com.planit.keeper.holiday.infra;

import com.planit.keeper.holiday.domain.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolidayRepository extends JpaRepository<Holiday,Long> {
    void deleteAllByCountryCode(String countryCode);

    void deleteAllByYear(int year);

    void deleteAllByYearAndCountryCode(int year, String countryCode);
}
