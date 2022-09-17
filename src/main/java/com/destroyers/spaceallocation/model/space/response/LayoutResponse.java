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
public class LayoutResponse {

    private String buildingName;
    private List<FloorResponse> floors;

}
