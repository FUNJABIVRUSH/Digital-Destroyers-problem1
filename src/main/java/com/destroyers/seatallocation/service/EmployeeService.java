package com.destroyers.seatallocation.service;

import com.destroyers.seatallocation.dao.EmployeeDao;
import com.destroyers.seatallocation.model.employee.EmployeeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeDao employeeDao;

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

    public EmployeeResponse getByPid(String pid) {
        return employeeDao.findByMpid(pid)
                .map(EmployeeResponse::from)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found for pid " + pid));
    }
}
