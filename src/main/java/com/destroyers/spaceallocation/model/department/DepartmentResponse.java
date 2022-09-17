package com.destroyers.spaceallocation.model.department;

import com.destroyers.spaceallocation.entities.Building;
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
    private Long buildingId;
    private String buildingName;
    private Long departmentId;
    private String departmentName;
    private Long oeCodeId;
    private String oeCodeName;
    private Integer totalEmployees;

    public static DepartmentResponse from(Department department, Optional<OECode> highLevelOECode) {
        Building building = department.getBuilding();
        return highLevelOECode
                .map( oeCode -> new DepartmentResponse(building.getId(), building.getName(), department.getId(), department.getName(), oeCode.getId(), oeCode.getName(), oeCode.getTotalEmployees()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "High level OE code is not mapped to department"));
    }

}
