package com.destroyers.spaceallocation.model.space;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class LayoutQueryResponse {
    private String buildingName;
    private Long floorId;
    private String floorName;
    private Long zoneId;
    private String zoneName;
    private Long seatId;
    private String seatNumber;
    private String seatType;
    private Boolean isReserved;
}

