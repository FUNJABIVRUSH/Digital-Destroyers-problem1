package com.destroyers.spaceallocation.dao;

import com.destroyers.spaceallocation.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@org.springframework.stereotype.Repository
public interface EmployeeDao extends JpaRepository<Employee, Long> {
    Optional<Employee> findByMpid(String mpid);
}
