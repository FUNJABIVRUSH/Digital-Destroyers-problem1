package com.destroyers.spaceallocation.dao;

import com.destroyers.spaceallocation.entities.SeatRange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRangeDao extends JpaRepository<SeatRange, Long> {
    List<SeatRange> findAllByOeCodeId(long l);
    List<SeatRange> findAllByEmployeeId(long l);
}
