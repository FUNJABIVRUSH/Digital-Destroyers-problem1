package com.destroyers.spaceallocation.model.seat.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
public class DeleteReservationRequest {
    private String pid;
    private LocalDate date;
}
