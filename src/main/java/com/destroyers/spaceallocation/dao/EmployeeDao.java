package com.destroyers.spaceallocation.dao;

import com.destroyers.spaceallocation.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Long> {
    Optional<Employee> findByMpid(String mpid);
}
