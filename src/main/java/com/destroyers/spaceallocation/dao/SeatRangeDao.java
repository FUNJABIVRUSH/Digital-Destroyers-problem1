package com.destroyers.spaceallocation.dao;

import com.destroyers.spaceallocation.entities.SeatRange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRangeDao extends JpaRepository<SeatRange, Long> {
}
