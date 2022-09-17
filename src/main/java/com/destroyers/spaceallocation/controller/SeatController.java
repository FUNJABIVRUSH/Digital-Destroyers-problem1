package com.destroyers.spaceallocation.controller;

import com.destroyers.spaceallocation.model.seat.request.SeatReservationRequest;
import com.destroyers.spaceallocation.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seat")
@CrossOrigin(origins = "*")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @PostMapping
    private Long reserveSeat(@RequestParam String pid,
                              SeatReservationRequest seatReservationRequest) {

        return seatService.reserve(pid, seatReservationRequest);
    }
}
