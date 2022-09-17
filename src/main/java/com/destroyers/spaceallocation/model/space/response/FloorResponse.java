package com.destroyers.spaceallocation.model.space.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class FloorResponse {

    private Long floorId;
    private String floorName;
    private List<ZoneResponse> zones;

}
