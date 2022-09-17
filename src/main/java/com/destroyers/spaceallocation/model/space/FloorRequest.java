package com.destroyers.spaceallocation.model.space;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FloorRequest {
    private Long floorId;
    private Long zoneId;
    private Long startSeatId;
    private Long endSeatId;
}
