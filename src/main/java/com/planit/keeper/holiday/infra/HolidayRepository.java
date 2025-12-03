package com.planit.keeper.holiday.infra;

import com.planit.keeper.holiday.domain.Holiday;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {
    void deleteAllByCountryCode(String countryCode);

    void deleteAllByHolidayYear(int holidayYear);

    void deleteAllByHolidayYearAndCountryCode(int holidayYear, String countryCode);

    Page<Holiday> findAllByCountryCode(String countryCode, Pageable pageable);

    Page<Holiday> findAllByHolidayYear(int holidayYear, Pageable pageable);

    Page<Holiday> findAllByHolidayYearAndCountryCode(int year, String countryCode, Pageable pageable);
}
