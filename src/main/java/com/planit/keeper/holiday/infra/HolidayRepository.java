package com.planit.keeper.holiday.infra;

import com.planit.keeper.holiday.domain.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolidayRepository extends JpaRepository<Holiday,Long> {
}
