package com.destroyers.spaceallocation.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
public class DateTimeRange {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String startTime;
    private String endTime;

    @JsonFormat(pattern = "HH:mm")
    public LocalTime getStartTime() {
        return LocalTime.parse(startTime);
    }
    @JsonFormat(pattern = "HH:mm")
    public LocalTime getEndTime() {
        return LocalTime.parse(endTime);
    }
}
