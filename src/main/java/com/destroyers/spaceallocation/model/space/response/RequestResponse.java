package com.destroyers.spaceallocation.model.space.response;

import com.destroyers.spaceallocation.entities.SpaceRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RequestResponse {
    private Long requestId;
    private Boolean isApproved;
    private LocalDate createdDate;
    private Long raisedWithOECodeId;
    private Long startSeatId;
    private Long endSeatId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    public static RequestResponse from(SpaceRequest request) {
        return new RequestResponse(request.getId(), request.getIsApproved(), request.getCreatedDate(), request.getOwnerOeCodeId().getId()
                , request.getRequestSpaceId().getRange().getStartSeat().getId(), request.getRequestSpaceId().getRange().getEndSeat().getId(),
                request.getRequestSpaceId().getStartDate(), request.getRequestSpaceId().getEndDate());
    }
}
