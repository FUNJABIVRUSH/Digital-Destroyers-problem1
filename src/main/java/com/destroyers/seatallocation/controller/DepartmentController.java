package com.destroyers.seatallocation.controller;

import com.destroyers.seatallocation.model.department.DepartmentResponse;
import com.destroyers.seatallocation.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping()
    public List<DepartmentResponse> getAll(){
        return departmentService.getAll();
    }
}
