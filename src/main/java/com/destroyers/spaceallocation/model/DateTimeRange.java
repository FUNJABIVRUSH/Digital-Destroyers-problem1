package com.destroyers.spaceallocation.model;


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
    private LocalDate date;
    private String startTime;
    private String endTime;

    public LocalTime getStartTime() {
        return LocalTime.parse(startTime);
    }

    public LocalTime getEndTime() {
        return LocalTime.parse(endTime);
    }
}
