package com.destroyers.seatallocation.model.employee;

import com.destroyers.seatallocation.entities.Employee;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class EmployeeResponse {
    private String mpid;
    private String name;
    private EmployeeRole role;

    public static EmployeeResponse from(Employee employee) {
        return new EmployeeResponse(employee.getMpid(), employee.getName(), employee.getRole());
    }
}
