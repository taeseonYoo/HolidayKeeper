package com.planit.keeper.holiday.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;

@Getter
public class PagedHolidayResponse {
    private final List<HolidayResponse> content;
    @Schema(description = "현재 페이지 번호",example = "0")
    private final int page;
    @Schema(description = "페이지당 항목 수",example = "20")
    private final int size;
    @Schema(description = "전체 항목 수",example = "1624")
    private final long totalElements;
    @Schema(description = "전체 페이지 수",example = "65")
    private final int totalPages;

    public PagedHolidayResponse(List<HolidayResponse> content, int page, int size, long totalElements, int totalPages) {
        this.content = content;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }
}
