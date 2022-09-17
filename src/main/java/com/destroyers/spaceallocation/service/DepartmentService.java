package com.destroyers.spaceallocation.service;

import com.destroyers.spaceallocation.dao.OECodeDao;
import com.destroyers.spaceallocation.entities.Department;
import com.destroyers.spaceallocation.entities.DepartmentAdmin;
import com.destroyers.spaceallocation.entities.OECode;
import com.destroyers.spaceallocation.model.department.DepartmentResponse;
import com.destroyers.spaceallocation.model.employee.EmployeeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    @Autowired
    private OECodeDao oeCodeDao;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentAdminService departmentAdminService;

    public List<DepartmentResponse> getAll(String pid) {
        EmployeeResponse employee = employeeService.getByPid(pid);
        return departmentAdminService.getByEmployeeId(employee.getId())
                .stream()
                .map(DepartmentAdmin::getDepartment)
                .map(this::getDepartmentResponse)
                .collect(Collectors.toList());
    }

    private DepartmentResponse getDepartmentResponse(Department department) {
        Optional<OECode> highLevelOECode = oeCodeDao.findByDepartmentId(department.getId()).stream()
                .filter(oeCode -> "HIGH".equalsIgnoreCase(oeCode.getType()))
                .findFirst();
        return DepartmentResponse.from(department, highLevelOECode);
    }
}
