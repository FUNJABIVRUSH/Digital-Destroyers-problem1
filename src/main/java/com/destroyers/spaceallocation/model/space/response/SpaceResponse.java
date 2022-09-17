package com.destroyers.spaceallocation.model.space.response;

import com.destroyers.spaceallocation.entities.Floor;
import com.destroyers.spaceallocation.entities.Seat;
import com.destroyers.spaceallocation.entities.SeatRange;
import com.destroyers.spaceallocation.entities.Zone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SpaceResponse {
    private Long floorId;
    private Long zoneId;
    private Long startSeatId;
    private Long endSeatId;

    public static SpaceResponse from(SeatRange seatRange) {
        Seat startSeat = seatRange.getStartSeat();
        Seat endSeat = seatRange.getEndSeat();
        Zone zone = startSeat.getZone();
        Floor floor = zone.getFloor();
        return new SpaceResponse(floor.getId(), zone.getId(), startSeat.getId(), endSeat.getId());
    }
}
