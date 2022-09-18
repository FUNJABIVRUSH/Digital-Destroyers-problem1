package com.destroyers.spaceallocation.controller;

import com.destroyers.spaceallocation.model.employee.EmployeeResponse;
import com.destroyers.spaceallocation.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "*")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeResponse> getEmployees(@RequestParam(required = false) Optional<Long> oeCodeId){
        return employeeService.getEmployees(oeCodeId);
    }

    @GetMapping("/{pid}")
    public EmployeeResponse getEmployeeByPid(@PathVariable("pid") String pid){
        return employeeService.getByPid(pid);
    }
}
