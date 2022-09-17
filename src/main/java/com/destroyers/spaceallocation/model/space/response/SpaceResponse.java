package com.destroyers.spaceallocation.model.space.response;

import com.destroyers.spaceallocation.entities.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SpaceResponse {
    private Long spaceId;
    private Long floorId;
    private Long zoneId;
    private Long startSeatId;
    private Long endSeatId;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate;

    public static SpaceResponse from(Space space) {
        Seat startSeat = space.getRange().getStartSeat();
        Seat endSeat = space.getRange().getEndSeat();
        Zone zone = startSeat.getZone();
        Floor floor = zone.getFloor();
        return new SpaceResponse(space.getId(),floor.getId(), zone.getId(), startSeat.getId(), endSeat.getId(),space.getStartDate(),space.getEndDate());
    }
}
