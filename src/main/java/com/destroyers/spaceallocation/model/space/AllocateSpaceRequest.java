package com.destroyers.spaceallocation.model.space;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@Data
public class AllocateSpaceRequest {

    private Long buildingId;
    private Long departmentId;
    private List<FloorRequest> floorRequests;
    private Long oeCodeId;

}
