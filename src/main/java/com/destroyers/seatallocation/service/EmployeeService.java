package com.destroyers.seatallocation.service;

import com.destroyers.seatallocation.dao.EmployeeDao;
import com.destroyers.seatallocation.model.employee.EmployeeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private EmployeeDao employeeDao;

    @Autowired
    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public List<EmployeeResponse> getAll() {
        return employeeDao.findAll()
                .stream()
                .map(EmployeeResponse::from)
                .collect(Collectors.toList());
    }
}
