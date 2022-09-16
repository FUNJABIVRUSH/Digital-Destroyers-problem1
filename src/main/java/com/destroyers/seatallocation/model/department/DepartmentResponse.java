package com.destroyers.seatallocation.model.department;

import com.destroyers.seatallocation.entities.Department;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepartmentResponse {
    private String name;
    private Integer totalEmployees;

    public static DepartmentResponse from(Department department, Integer totalEmployees) {
        return new DepartmentResponse(department.getName(), totalEmployees);
    }

}
