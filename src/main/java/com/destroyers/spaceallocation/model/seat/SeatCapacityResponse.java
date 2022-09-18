package com.destroyers.spaceallocation.model.seat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SeatCapacityResponse {
    Integer maxSeatAllocationPercent;
}
