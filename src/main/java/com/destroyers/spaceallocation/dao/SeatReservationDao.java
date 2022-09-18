package com.destroyers.spaceallocation.dao;

import com.destroyers.spaceallocation.entities.SeatReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface SeatReservationDao extends JpaRepository<SeatReservation, Long> {

    List<SeatReservation> findAllBySeatIdInAndReservationDate(List<Long> seatId, LocalDate reservationDate);
    List<SeatReservation> findAllByEmployeeIdAndSeatIdAndReservationDate(Long employeeId, Long seatId, LocalDate reservationDate);
}
