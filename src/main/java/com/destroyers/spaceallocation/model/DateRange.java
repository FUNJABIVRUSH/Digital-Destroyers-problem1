package com.destroyers.spaceallocation.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
public class DateRange {
    private LocalDate startDate;
    private LocalDate endDate;
}
