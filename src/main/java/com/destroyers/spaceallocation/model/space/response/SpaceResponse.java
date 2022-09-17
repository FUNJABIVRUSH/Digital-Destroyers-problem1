package com.destroyers.spaceallocation.model.space.response;

import com.destroyers.spaceallocation.entities.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SpaceResponse {
    private Long floorId;
    private Long zoneId;
    private Long startSeatId;
    private Long endSeatId;
    private LocalDate startDate;
    private LocalDate endDate;

    public static SpaceResponse from(SeatRange seatRange, Space space) {
        Seat startSeat = seatRange.getStartSeat();
        Seat endSeat = seatRange.getEndSeat();
        Zone zone = startSeat.getZone();
        Floor floor = zone.getFloor();
        return new SpaceResponse(floor.getId(), zone.getId(), startSeat.getId(), endSeat.getId(),space.getStartDate(),space.getEndDate());
    }
}
