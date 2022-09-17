package com.destroyers.spaceallocation.service;


import com.destroyers.spaceallocation.dao.SeatDao;
import com.destroyers.spaceallocation.entities.Seat;
import com.destroyers.spaceallocation.model.seat.request.SeatReservationRequest;
import com.destroyers.spaceallocation.model.space.response.SpaceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SeatService {

    private final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private SpaceService spaceService;

    @Autowired
    private SeatDao seatDao;

    public Long reserve(String pid, SeatReservationRequest seatReservationRequest) {
        Long seatId = seatReservationRequest.getSeatId();
        if (!isUserAllowedToReserveSeat(pid, seatId)) {
            LOGGER.error("Not allowed to reserve seat, this seat is now allocated for your team");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not allowed to reserve seat, this seat is now allocated for your team");
        }
        Seat seat = getSeat(seatId);
        if (seat.getIsReserved()) {
            LOGGER.error("Seat already reserved");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Seat already reserved");
        }
        Seat reservedSeat = seat.toBuilder().isReserved(true).build();
        return seatDao.save(reservedSeat).getId();
    }

    private Seat getSeat(Long seatId) {
        return seatDao.findById(seatId)
                .orElseThrow(() -> {
                    LOGGER.error("Seat not found for {}", seatId);
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Seat not found for " + seatId);
                });
    }

    private boolean isUserAllowedToReserveSeat(String pid, Long seatId) {
        List<SpaceResponse> spaceAllocatedToOE = spaceService.getSpaceAllocatedTo(pid);
        return spaceAllocatedToOE.stream()
                .anyMatch(spaceResponse -> spaceResponse.getStartSeatId() <= seatId && seatId <= spaceResponse.getEndSeatId());
    }
}
