package com.destroyers.spaceallocation.service;

import com.destroyers.spaceallocation.dao.DepartmentDao;
import com.destroyers.spaceallocation.dao.OECodeDao;
import com.destroyers.spaceallocation.entities.Department;
import com.destroyers.spaceallocation.entities.OECode;
import com.destroyers.spaceallocation.model.department.DepartmentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private OECodeDao oeCodeDao;

    public List<DepartmentResponse> getAll() {
        return departmentDao.findAll()
                .stream()
                .map(this::getDepartmentResponse)
                .collect(Collectors.toList());
    }

    private DepartmentResponse getDepartmentResponse(Department department) {
        Integer totalEmployees = oeCodeDao.findByDepartmentId(department.getId()).stream()
                .map(OECode::getTotalEmployees)
                .reduce(0, Integer::sum);
        return DepartmentResponse.from(department, totalEmployees);
    }
}
