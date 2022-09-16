package com.destroyers.spaceallocation.service;

import com.destroyers.spaceallocation.dao.EmployeeDao;
import com.destroyers.spaceallocation.model.employee.EmployeeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeDao employeeDao;

    private final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

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
                .orElseThrow(() -> {
                    LOGGER.error("Employee not found. pid: {}",pid);
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found for pid " + pid);
                });
    }
}
