package com.destroyers.spaceallocation.model.seat.request;

import com.destroyers.spaceallocation.model.DateTimeRange;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
public class SeatUpdateRequest {
    private Long originalSeatId;
    private Long newSeatId;
    private String pid;
    private LocalDate originalDate;
    private DateTimeRange newDateTime;
}
