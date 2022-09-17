package com.destroyers.spaceallocation.model.employee;

import com.destroyers.spaceallocation.entities.Employee;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class EmployeeResponse {
    private Long id;
    private String mpid;
    private String name;
    private EmployeeRole role;

    public static EmployeeResponse from(Employee employee) {
        return new EmployeeResponse(employee.getId(), employee.getMpid(), employee.getName(), employee.getRole());
    }
}
