package com.destroyers.spaceallocation.model.department;


import com.destroyers.spaceallocation.entities.OECode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OECodeResponse {
    private Long id;
    private String name;
    private Integer totalEmployees;

    public static OECodeResponse from(OECode oeCode) {
        return new OECodeResponse(oeCode.getId(), oeCode.getName(), oeCode.getTotalEmployees());
    }
}
