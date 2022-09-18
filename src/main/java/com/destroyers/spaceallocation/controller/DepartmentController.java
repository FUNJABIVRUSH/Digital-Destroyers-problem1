package com.destroyers.spaceallocation.controller;

import com.destroyers.spaceallocation.model.department.DepartmentResponse;
import com.destroyers.spaceallocation.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
@CrossOrigin(origins = "*")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    @CrossOrigin(origins = "*")
    public List<DepartmentResponse> getAll(@RequestParam("pid") String pid){
        return departmentService.getAll(pid);
    }
}
