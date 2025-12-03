package com.planit.keeper.holiday.infra;

import com.planit.keeper.holiday.domain.Holiday;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {
    void deleteAllByCountryCode(String countryCode);

    void deleteAllByYear(int year);

    void deleteAllByYearAndCountryCode(int year, String countryCode);

    Page<Holiday> findAllByCountryCode(String countryCode, Pageable pageable);

    Page<Holiday> findAllByYear(int year, Pageable pageable);

    Page<Holiday> findAllByYearAndCountryCode(int year, String countryCode, Pageable pageable);
}
