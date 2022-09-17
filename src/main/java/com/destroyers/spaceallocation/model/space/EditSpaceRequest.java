package com.destroyers.spaceallocation.model.space;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EditSpaceRequest {
    @NonNull
    Long oeCodeId;
    @NonNull
    Long startSeatId;
    @NonNull
    Long endSeatId;

}
