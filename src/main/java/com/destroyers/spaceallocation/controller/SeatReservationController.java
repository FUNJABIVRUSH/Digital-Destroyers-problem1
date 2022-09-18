package com.destroyers.spaceallocation.controller;

import com.destroyers.spaceallocation.model.seat.request.DeleteReservationRequest;
import com.destroyers.spaceallocation.model.seat.request.SeatReservationRequest;
import com.destroyers.spaceallocation.service.SeatReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seat/reservation")
@CrossOrigin(origins = "*")
public class SeatReservationController {

    @Autowired
    private SeatReservationService seatReservationService;

    @PostMapping
    private List<Long> reserveSeat(@RequestBody SeatReservationRequest seatReservationRequest) {
        return seatReservationService.reserve(seatReservationRequest);
    }

    @DeleteMapping
    private void deleteReservation(@RequestBody List<DeleteReservationRequest> requests){
        seatReservationService.deleteReservations(requests);
    }
}
