package com.destroyers.spaceallocation.service;

import com.destroyers.spaceallocation.model.space.SpaceQueryResponse;
import com.destroyers.spaceallocation.model.space.response.FloorResponse;
import com.destroyers.spaceallocation.model.space.response.SeatResponse;
import com.destroyers.spaceallocation.model.space.response.LayoutResponse;
import com.destroyers.spaceallocation.model.space.response.ZoneResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LayoutService {

    @Autowired
    private EntityManager entityManager;

    public LayoutResponse getLayout(Long buildingId){

        List<SpaceQueryResponse> spaceQueryResponses = entityManager.createNativeQuery("select  building.name as buildingName, floor.id as floorId, floor.name as floorName, zone.id as zoneId, zone.name as zoneName, seat.id as seatId, seat.number as seatNumber, seat.type as seatType, seat.is_reserved as isReserved\n" +
                        "from building join floor on building.id = floor.building_id join zone on zone.floor_id = floor.id join seat on seat.zone_id = zone.id\n" +
                        "where building.id = ?", "SpaceQuery")
                .setParameter(1, buildingId)
                .getResultList();

        return getLayoutResponse(spaceQueryResponses);

    }

    private LayoutResponse getLayoutResponse(List<SpaceQueryResponse> spaceQueryResponses) {
        Map<Long, List<SpaceQueryResponse>> spaceResponseByFloorId = spaceQueryResponses.stream()
                .collect(Collectors.groupingBy(SpaceQueryResponse::getFloorId));

        List<FloorResponse> floorResponses = spaceResponseByFloorId.values()
                .stream()
                .map(this::getFloorResponses)
                .collect(Collectors.toList());
        SpaceQueryResponse firstSpaceResponse = spaceQueryResponses.get(0);
        return new LayoutResponse(firstSpaceResponse.getBuildingName(), floorResponses);
    }

    private FloorResponse getFloorResponses(List<SpaceQueryResponse> spaceQueryResponses) {

        Map<Long, List<SpaceQueryResponse>> spaceResponseByZoneId = spaceQueryResponses.stream()
                .collect(Collectors.groupingBy(SpaceQueryResponse::getZoneId));

        List<ZoneResponse> zoneResponses = spaceResponseByZoneId.values()
                .stream()
                .map(this::getZoneResponse)
                .collect(Collectors.toList());
        SpaceQueryResponse firstSpaceResponse = spaceQueryResponses.get(0);
        return new FloorResponse(firstSpaceResponse.getFloorId(), firstSpaceResponse.getFloorName(), zoneResponses);
    }


    private ZoneResponse getZoneResponse(List<SpaceQueryResponse> spaceQueryResponses) {

        List<SeatResponse> seatResponses = spaceQueryResponses.stream()
                .map(spaceResponse -> new SeatResponse(spaceResponse.getSeatId(), spaceResponse.getSeatNumber(), spaceResponse.getIsReserved(), spaceResponse.getSeatType()))
                .collect(Collectors.toList());

        SpaceQueryResponse firstSpaceResponse = spaceQueryResponses.get(0);
        return new ZoneResponse(firstSpaceResponse.getZoneId(), firstSpaceResponse.getZoneName(), seatResponses);
    }
}
