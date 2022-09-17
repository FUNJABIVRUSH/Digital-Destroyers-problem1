package com.destroyers.spaceallocation.model.seat.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
public class SeatRequest {
    private Long seatId;
    private String pid;
}
