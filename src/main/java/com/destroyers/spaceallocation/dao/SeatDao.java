package com.destroyers.spaceallocation.dao;

import com.destroyers.spaceallocation.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatDao extends JpaRepository<Seat, Long> {
}
