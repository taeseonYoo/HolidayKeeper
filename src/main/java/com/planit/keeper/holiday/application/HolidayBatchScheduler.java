package com.planit.keeper.holiday.application;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HolidayBatchScheduler {
    private final HolidayService holidayService;

    //1월 2일 01:00 KST에 데이터 동기화 스케줄러
    @Scheduled(cron = "0 0 1 2 1 *", zone = "Asia/Seoul")
    public void refreshHolidaysPerYear() {
        holidayService.refreshPreviousAndCurrentYear();
    }

}
