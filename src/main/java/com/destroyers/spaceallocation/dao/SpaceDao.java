package com.destroyers.spaceallocation.dao;

import com.destroyers.spaceallocation.entities.Employee;
import com.destroyers.spaceallocation.entities.OECode;
import com.destroyers.spaceallocation.entities.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpaceDao extends JpaRepository<Space, Long> {
    List<Space> findAllByAssignedOeCodeId(OECode assignedOeCodeId);
    List<Space> findAllByCreatedEmployeeId(Employee createdEmployeeId);
    void deleteByAssignedOeCodeId(OECode assignedOeCodeId);
}
