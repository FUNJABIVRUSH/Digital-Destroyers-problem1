package com.destroyers.spaceallocation.model.space.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SeatResponse {

    private Long seatId;
    private String seatNumber;
    private Boolean isReserved;
    private String type;
}
