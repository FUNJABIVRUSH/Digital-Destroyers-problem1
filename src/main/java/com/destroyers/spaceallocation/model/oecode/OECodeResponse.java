package com.destroyers.spaceallocation.model.oecode;

import com.destroyers.spaceallocation.entities.OECode;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class OECodeResponse {
    private Long id;
    private String name;

    public static OECodeResponse from(OECode oeCode) {
        return new OECodeResponse(oeCode.getId(), oeCode.getName());
    }
}
