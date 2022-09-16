package com.destroyers.seatallocation.dao;

import com.destroyers.seatallocation.entities.OECode;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

@org.springframework.stereotype.Repository
public interface OECodeDao extends JpaRepository<OECode, Long> {
    List<OECode> findByDepartmentId(Long departmentId);
}
