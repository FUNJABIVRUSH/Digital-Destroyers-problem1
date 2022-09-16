package com.destroyers.spaceallocation.dao;

import com.destroyers.spaceallocation.entities.OECode;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

@org.springframework.stereotype.Repository
public interface OECodeDao extends JpaRepository<OECode, Long> {
    List<OECode> findByDepartmentId(Long departmentId);
}
