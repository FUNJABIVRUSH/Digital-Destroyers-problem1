package com.destroyers.spaceallocation.dao;

import com.destroyers.spaceallocation.entities.DepartmentAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentAdminDao extends JpaRepository<DepartmentAdmin, Long> {
    List<DepartmentAdmin> findByEmployeeId(Long employeeId);
}
