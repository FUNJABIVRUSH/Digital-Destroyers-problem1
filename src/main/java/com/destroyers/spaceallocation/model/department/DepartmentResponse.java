package com.destroyers.spaceallocation.model.department;

import com.destroyers.spaceallocation.entities.Department;
import com.destroyers.spaceallocation.entities.OECode;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Data
@AllArgsConstructor
public class DepartmentResponse {
    private Long departmentId;
    private String departmentName;
    private Long oeCodeId;
    private String oeCodeName;
    private Integer totalEmployees;

    public static DepartmentResponse from(Department department, Optional<OECode> highLevelOECode) {
        return highLevelOECode
                .map( oeCode -> new DepartmentResponse(department.getId(), department.getName(), oeCode.getId(), oeCode.getName(), oeCode.getTotalEmployees()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "High level OE code is not mapped to department"));
    }

}
