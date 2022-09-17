package com.destroyers.spaceallocation.model.space;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@Data
public class AllocateSpaceRequest {

    private String buildingId;
    private String zoneId;
    private String startSeatNo;
    private String endSeatNo;
    private String departmentId;
    private List<String> oeCodes;

}
