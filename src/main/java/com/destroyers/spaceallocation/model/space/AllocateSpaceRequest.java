package com.destroyers.spaceallocation.model.space;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
public class AllocateSpaceRequest {

    private Long oeCodeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<FloorRequest> floorRequests;
}
