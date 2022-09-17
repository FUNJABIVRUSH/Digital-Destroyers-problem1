package com.destroyers.spaceallocation.model.seat.request;

import com.destroyers.spaceallocation.model.DateRange;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
public class SeatReservationRequest {
    private List<SeatRequest> seatRequests;
    private List<DateRange> dateRanges;
}
