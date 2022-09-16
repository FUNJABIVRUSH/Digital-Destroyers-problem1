package com.destroyers.seatallocation.model.employee;

import com.destroyers.seatallocation.entities.Employee;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class EmployeeResponse {
    private Long id;
    private String mpid;
    private String name;
    private EmployeeRole role;

    public static EmployeeResponse from(Employee employee) {
        return new EmployeeResponse(employee.getId(), employee.getMpid(), employee.getName(), employee.getRole());
    }
}
