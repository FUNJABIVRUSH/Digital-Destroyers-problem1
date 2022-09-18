package com.destroyers.spaceallocation.dao;

import com.destroyers.spaceallocation.entities.SeatReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SeatReservationDao extends JpaRepository<SeatReservation, Long> {

    List<SeatReservation> findAllBySeatIdInAndReservationDate(List<Long> seatId, LocalDate reservationDate);
    Optional<SeatReservation> findByEmployeeIdAndSeatIdAndReservationDate(Long employeeId, Long seatId, LocalDate reservationDate);
}
