package com.destroyers.spaceallocation.service;


import com.destroyers.spaceallocation.dao.EmployeeDao;
import com.destroyers.spaceallocation.dao.SeatDao;
import com.destroyers.spaceallocation.dao.SeatReservationDao;
import com.destroyers.spaceallocation.entities.Employee;
import com.destroyers.spaceallocation.entities.Seat;
import com.destroyers.spaceallocation.entities.SeatReservation;
import com.destroyers.spaceallocation.model.DateTimeRange;
import com.destroyers.spaceallocation.model.seat.request.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.destroyers.spaceallocation.util.DateTimeUtil.afterOrEqual;
import static com.destroyers.spaceallocation.util.DateTimeUtil.beforeOrEqual;

@Service
public class SeatReservationService {

    private final Logger LOGGER = LoggerFactory.getLogger(SeatReservationService.class);

    @Autowired
    private SeatDao seatDao;

    @Autowired
    private SeatReservationDao seatReservationDao;

    @Autowired
    private EmployeeDao employeeDao;

    public List<Long> reserve(SeatReservationRequest seatReservationRequest) {
        List<SeatReservation> seatReservations = seatReservationRequest.getSeatRequests().stream()
                .flatMap(this::getSeatReservations)
                .collect(Collectors.toList());
        return seatReservationDao.saveAll(seatReservations).stream()
                .map(SeatReservation::getId)
                .collect(Collectors.toList());
    }

    public void deleteReservations(List<DeleteReservationRequest> requests) {
        List<SeatReservation> reservations = requests.stream()
                .map(request -> getSeatReservationFor(request.getPid(), request.getSeatId(), request.getDate()))
                .collect(Collectors.toList());
        seatReservationDao.deleteAll(reservations);
    }

    public List<Long> update(List<SeatUpdateRequest> seatUpdateRequests) {
        List<SeatReservation> updatedReservations = seatUpdateRequests.stream()
                .map(this::getUpdatedReservations)
                .collect(Collectors.toList());

        return seatReservationDao.saveAll(updatedReservations)
                .stream()
                .map(SeatReservation::getId)
                .collect(Collectors.toList());
    }

    private SeatReservation getSeatReservationFor(String pid, Long seatId, LocalDate date) {
        Employee employee = getEmployee(pid);
        return seatReservationDao.findByEmployeeIdAndSeatIdAndReservationDate(employee.getId(), seatId, date)
                .orElseThrow(() -> {
                    LOGGER.error("Seat reservation not found. pid: {}, seatId: {}, date: {}", pid, seatId, date);
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found for employeeId " + pid + " seatId:" + seatId + " date:" + date);
                });
    }

    private Stream<SeatReservation> getSeatReservations(SeatRequest seatRequest) {
        Seat seat = getSeat(seatRequest.getSeatId());
        Employee employee = getEmployee(seatRequest.getPid());
        return seatRequest.getDateTimeRange()
                .stream()
                .peek(dateRange -> checkIfSeatIsReserved(seat.getId(), dateRange))
                .map(dateRange -> new SeatReservation(null, seat, dateRange.getDate(), dateRange.getStartTime(), dateRange.getEndTime(), employee));
    }

    private Seat getSeat(Long seatId) {
        return seatDao.findById(seatId)
                .orElseThrow(() -> {
                    LOGGER.error("Seat not found for {}", seatId);
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Seat not found for " + seatId);
                });
    }

    private Employee getEmployee(String pid) {
        return employeeDao.findByMpid(pid).orElseThrow(() -> {
            LOGGER.error("Employee not found. pid: {}", pid);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found for employeeId " + pid);
        });
    }

    private void checkIfSeatIsReserved(Long seatId, DateTimeRange dateTimeRange) {
        LocalTime startTime = dateTimeRange.getStartTime();
        LocalTime endTime = dateTimeRange.getEndTime();
        List<SeatReservation> reservationsBetween = seatReservationDao.findAllBySeatIdInAndReservationDate(List.of(seatId), dateTimeRange.getDate()).stream()
                .filter(seatReservation ->
                        (beforeOrEqual(seatReservation.getStartTime(), startTime) && seatReservation.getEndTime().isAfter(startTime)) ||
                                (afterOrEqual(seatReservation.getStartTime(), endTime) && beforeOrEqual(seatReservation.getEndTime(), endTime)) ||
                                (afterOrEqual(seatReservation.getStartTime(), startTime) && beforeOrEqual(seatReservation.getEndTime(), endTime))
                )
                .collect(Collectors.toList());
        if (!reservationsBetween.isEmpty()) {
            LOGGER.error("Seat already reserved");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Seat already reserved for given date & time");
        }
    }

    private SeatReservation getUpdatedReservations(SeatUpdateRequest request) {
        SeatReservation seatReservation = getSeatReservationFor(request.getPid(), request.getOriginalSeatId(), request.getOriginalDate());
        Seat newSeat = getSeat(request.getNewSeatId());
        return seatReservation.toBuilder()
                .reservationDate(request.getNewDateTime().getDate())
                .startTime(request.getNewDateTime().getStartTime())
                .endTime(request.getNewDateTime().getEndTime())
                .seat(newSeat)
                .build();
    }

}
