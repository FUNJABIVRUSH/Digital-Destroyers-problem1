package com.destroyers.spaceallocation.controller;

import com.destroyers.spaceallocation.model.seat.SeatCapacityResponse;
import com.destroyers.spaceallocation.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seat")
@CrossOrigin(origins = "*")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @GetMapping("/capacity")
    public SeatCapacityResponse getMaxSeatCapacityPerBuilding(@RequestParam Long buildingId, @RequestParam String pid) {
        return seatService.getSeatCapacity(buildingId,pid);
    }
}
