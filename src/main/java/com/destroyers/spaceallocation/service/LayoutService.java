package com.destroyers.spaceallocation.service;

import com.destroyers.spaceallocation.dao.CustomNativeQueryExecutor;
import com.destroyers.spaceallocation.model.space.LayoutQueryResponse;
import com.destroyers.spaceallocation.model.space.response.FloorResponse;
import com.destroyers.spaceallocation.model.space.response.LayoutResponse;
import com.destroyers.spaceallocation.model.space.response.SeatResponse;
import com.destroyers.spaceallocation.model.space.response.ZoneResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LayoutService {

    @Autowired
    private final CustomNativeQueryExecutor nativeQueryExecutor;

    public LayoutService(CustomNativeQueryExecutor layoutDao) {
        this.nativeQueryExecutor = layoutDao;
    }

    public LayoutResponse getLayout(Long buildingId){
        return getLayoutResponse(nativeQueryExecutor.getLayoutByBuildingId(buildingId));
    }

    private LayoutResponse getLayoutResponse(List<LayoutQueryResponse> spaceQueryResponses) {
        Map<Long, List<LayoutQueryResponse>> spaceResponseByFloorId = spaceQueryResponses.stream()
                .collect(Collectors.groupingBy(LayoutQueryResponse::getFloorId));

        List<FloorResponse> floorResponses = spaceResponseByFloorId.values()
                .stream()
                .map(this::getFloorResponses)
                .collect(Collectors.toList());
        LayoutQueryResponse firstSpaceResponse = spaceQueryResponses.get(0);
        return new LayoutResponse(firstSpaceResponse.getBuildingName(), floorResponses);
    }

    private FloorResponse getFloorResponses(List<LayoutQueryResponse> spaceQueryResponses) {

        Map<Long, List<LayoutQueryResponse>> spaceResponseByZoneId = spaceQueryResponses.stream()
                .collect(Collectors.groupingBy(LayoutQueryResponse::getZoneId));

        List<ZoneResponse> zoneResponses = spaceResponseByZoneId.values()
                .stream()
                .map(this::getZoneResponse)
                .collect(Collectors.toList());
        LayoutQueryResponse firstSpaceResponse = spaceQueryResponses.get(0);
        return new FloorResponse(firstSpaceResponse.getFloorId(), firstSpaceResponse.getFloorName(), zoneResponses);
    }


    private ZoneResponse getZoneResponse(List<LayoutQueryResponse> spaceQueryResponses) {

        List<SeatResponse> seatResponses = spaceQueryResponses.stream()
                .map(spaceResponse -> new SeatResponse(spaceResponse.getSeatId(), spaceResponse.getSeatNumber(), spaceResponse.getIsReserved(), spaceResponse.getSeatType()))
                .collect(Collectors.toList());

        LayoutQueryResponse firstSpaceResponse = spaceQueryResponses.get(0);
        return new ZoneResponse(firstSpaceResponse.getZoneId(), firstSpaceResponse.getZoneName(), seatResponses);
    }
}
