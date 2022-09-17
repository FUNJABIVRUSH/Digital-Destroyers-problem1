package com.destroyers.spaceallocation.dao;

import com.destroyers.spaceallocation.entities.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpaceDao extends JpaRepository<Space, Long> {
    List<Space> findAllByAssignedOeCodeId(long assignedOeCodeId);
    List<Space> findAllByCreatedEmployeeId(long createdEmployeeId);
}
