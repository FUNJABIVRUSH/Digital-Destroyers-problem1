package com.destroyers.spaceallocation.controller;

import com.destroyers.spaceallocation.model.employee.EmployeeResponse;
import com.destroyers.spaceallocation.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<EmployeeResponse> getEmployees(){
        return employeeService.getAll();
    }

    @GetMapping("/{pid}")
    public EmployeeResponse getEmployeeByPid(@PathVariable("pid") String pid){
        return employeeService.getByPid(pid);
    }
}
