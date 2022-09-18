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
public class SpaceRequestResponseWrapper {
    private List<RequestResponse> requestToOther;
    private List<RequestResponse> requestToMe;
}
