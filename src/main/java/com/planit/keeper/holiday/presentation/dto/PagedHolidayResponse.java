package com.planit.keeper.holiday.presentation.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class PagedHolidayResponse {
    List<HolidayResponse> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;

    public PagedHolidayResponse(List<HolidayResponse> content, int page, int size, long totalElements, int totalPages) {
        this.content = content;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }
}
