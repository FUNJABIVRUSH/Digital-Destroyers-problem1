package com.destroyers.spaceallocation.model.employee;

import com.destroyers.spaceallocation.entities.Employee;
import com.destroyers.spaceallocation.entities.OECode;
import com.destroyers.spaceallocation.model.oecode.OECodeResponse;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class EmployeeResponse {
    private Long id;
    private String mpid;
    private String name;
    private EmployeeRole role;
    private OECodeResponse oeCode;
    private List<OECodeResponse> childOECodes;

    public static EmployeeResponse from(Employee employee, List<OECode> oeCodes) {
        OECode oeCode = employee.getOeCode();
        List<OECodeResponse> childOECodes = oeCodes.stream()
                .map(OECodeResponse::from)
                .collect(Collectors.toList());
        return new EmployeeResponse(employee.getId(), employee.getMpid(), employee.getName(), employee.getRole(), OECodeResponse.from(oeCode), childOECodes);
    }
}
