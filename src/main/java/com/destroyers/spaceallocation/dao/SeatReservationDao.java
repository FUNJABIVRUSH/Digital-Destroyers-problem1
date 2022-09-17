package com.destroyers.spaceallocation.dao;

import com.destroyers.spaceallocation.entities.SeatReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface SeatReservationDao extends JpaRepository<SeatReservation, Long> {

    @Query(value = "select * from seat_reservation where seat_id = :seatId AND ((start_date <= :startDate AND end_date >= :startDate) OR (start_date <= :endDate AND end_date >= :endDate))", nativeQuery = true)
    List<SeatReservation> getReservationsBetween(Long seatId, LocalDate startDate, LocalDate endDate);
}
