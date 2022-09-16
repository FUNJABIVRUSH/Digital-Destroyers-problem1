package com.destroyers.seatallocation.dao;

import com.destroyers.seatallocation.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface DepartmentDao extends JpaRepository<Department, Long> {
}
