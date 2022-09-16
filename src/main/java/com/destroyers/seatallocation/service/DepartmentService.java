package com.destroyers.seatallocation.service;

import com.destroyers.seatallocation.dao.DepartmentDao;
import com.destroyers.seatallocation.dao.OECodeDao;
import com.destroyers.seatallocation.entities.Department;
import com.destroyers.seatallocation.entities.OECode;
import com.destroyers.seatallocation.model.department.DepartmentResponse;
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
