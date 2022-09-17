package com.destroyers.spaceallocation.service;


import com.destroyers.spaceallocation.dao.SeatDao;
import com.destroyers.spaceallocation.dao.SeatReservationDao;
import com.destroyers.spaceallocation.entities.Seat;
import com.destroyers.spaceallocation.entities.SeatReservation;
import com.destroyers.spaceallocation.model.DateRange;
import com.destroyers.spaceallocation.model.seat.request.SeatReservationRequest;
import com.destroyers.spaceallocation.model.space.response.SpaceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatService {

    private final Logger LOGGER = LoggerFactory.getLogger(SeatService.class);

    @Autowired
    private SpaceService spaceService;

    @Autowired
    private SeatDao seatDao;

    @Autowired
    private SeatReservationDao seatReservationDao;

    public List<Long> reserve(String pid, SeatReservationRequest seatReservationRequest) {
        Long seatId = seatReservationRequest.getSeatId();
        if (!isUserAllowedToReserveSeat(pid, seatId)) {
            LOGGER.error("Not allowed to reserve seat, this seat is now allocated for your team");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not allowed to reserve seat, this seat is now allocated for your team");
        }
        Seat seat = getSeat(seatId);
        List<SeatReservation> seatReservations = getSeatReservations(seatReservationRequest, seat);

        return seatReservationDao.saveAll(seatReservations).stream()
                .map(SeatReservation::getId)
                .collect(Collectors.toList());
    }

    private List<SeatReservation> getSeatReservations(SeatReservationRequest seatReservationRequest, Seat seat) {
        return seatReservationRequest.getDateRanges()
                .stream()
                .peek(dateRange -> checkIfSeatIsReserved(seat.getId(), dateRange))
                .map(dateRange -> new SeatReservation(null, seat, dateRange.getStartDate(), dateRange.getEndDate()))
                .collect(Collectors.toList());
    }

    private Seat getSeat(Long seatId) {
        return seatDao.findById(seatId)
                .orElseThrow(() -> {
                    LOGGER.error("Seat not found for {}", seatId);
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Seat not found for " + seatId);
                });
    }

    private void checkIfSeatIsReserved(Long seatId, DateRange dateRange){
        List<SeatReservation> reservationsBetween = seatReservationDao.getReservationsBetween(seatId, dateRange.getStartDate(), dateRange.getEndDate());
        if( !reservationsBetween.isEmpty()){
            LOGGER.error("Seat already reserved");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Seat already reserved for given dates");
        }
    }
    private boolean isUserAllowedToReserveSeat(String pid, Long seatId) {
        List<SpaceResponse> spaceAllocatedToOE = spaceService.getSpaceAllocatedTo(pid);
        return spaceAllocatedToOE.stream()
                .anyMatch(spaceResponse -> spaceResponse.getStartSeatId() <= seatId && seatId <= spaceResponse.getEndSeatId());
    }
}
