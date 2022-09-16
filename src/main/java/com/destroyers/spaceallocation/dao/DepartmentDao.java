package com.destroyers.spaceallocation.dao;

import com.destroyers.spaceallocation.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface DepartmentDao extends JpaRepository<Department, Long> {
}
