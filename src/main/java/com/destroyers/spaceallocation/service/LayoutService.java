package com.destroyers.spaceallocation.service;

import com.destroyers.spaceallocation.dao.CustomNativeQueryExecutor;
import com.destroyers.spaceallocation.dao.SeatReservationDao;
import com.destroyers.spaceallocation.entities.Seat;
import com.destroyers.spaceallocation.entities.SeatReservation;
import com.destroyers.spaceallocation.model.DateTimeRange;
import com.destroyers.spaceallocation.model.space.LayoutQueryResponse;
import com.destroyers.spaceallocation.model.space.response.FloorResponse;
import com.destroyers.spaceallocation.model.space.response.LayoutResponse;
import com.destroyers.spaceallocation.model.space.response.SeatResponse;
import com.destroyers.spaceallocation.model.space.response.ZoneResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.destroyers.spaceallocation.util.DateTimeUtil.afterOrEqual;
import static com.destroyers.spaceallocation.util.DateTimeUtil.beforeOrEqual;

@Service
public class LayoutService {

    @Autowired
    private final CustomNativeQueryExecutor nativeQueryExecutor;

    @Autowired
    private final SeatReservationDao seatReservationDao;

    public LayoutService(CustomNativeQueryExecutor layoutDao, SeatReservationDao seatReservationDao) {
        this.nativeQueryExecutor = layoutDao;
        this.seatReservationDao = seatReservationDao;
    }

    public LayoutResponse getLayout(Long buildingId, DateTimeRange dateTimeRange) {
        return getLayoutResponse(nativeQueryExecutor.getLayoutByBuildingId(buildingId), dateTimeRange);
    }

    private LayoutResponse getLayoutResponse(List<LayoutQueryResponse> spaceQueryResponses, DateTimeRange dateTimeRange) {
        Set<Long> reservedSeatIds = Objects.isNull(dateTimeRange) ? Set.of() :
                getReservedSeatIds(spaceQueryResponses, dateTimeRange);

        Map<Long, List<LayoutQueryResponse>> spaceResponseByFloorId = spaceQueryResponses.stream()
                .collect(Collectors.groupingBy(LayoutQueryResponse::getFloorId));

        List<FloorResponse> floorResponses = spaceResponseByFloorId.values()
                .stream()
                .map(spaceQueryResponsesForFloor -> getFloorResponses(spaceQueryResponsesForFloor, reservedSeatIds))
                .collect(Collectors.toList());
        LayoutQueryResponse firstSpaceResponse = spaceQueryResponses.get(0);
        return new LayoutResponse(firstSpaceResponse.getBuildingName(), floorResponses);
    }

    private FloorResponse getFloorResponses(List<LayoutQueryResponse> spaceQueryResponses, Set<Long> reservedSeatIds) {

        Map<Long, List<LayoutQueryResponse>> spaceResponseByZoneId = spaceQueryResponses.stream()
                .collect(Collectors.groupingBy(LayoutQueryResponse::getZoneId));

        List<ZoneResponse> zoneResponses = spaceResponseByZoneId.values()
                .stream()
                .map(spaceQueryResponsesForZone -> getZoneResponse(spaceQueryResponsesForZone, reservedSeatIds))
                .collect(Collectors.toList());
        LayoutQueryResponse firstSpaceResponse = spaceQueryResponses.get(0);
        return new FloorResponse(firstSpaceResponse.getFloorId(), firstSpaceResponse.getFloorName(), zoneResponses);
    }


    private ZoneResponse getZoneResponse(List<LayoutQueryResponse> spaceQueryResponses, Set<Long> reservedSeatIds) {
        List<SeatResponse> seatResponses = spaceQueryResponses.stream()
                .map(spaceResponse -> getSeatResponse(reservedSeatIds, spaceResponse))
                .collect(Collectors.toList());

        LayoutQueryResponse firstSpaceResponse = spaceQueryResponses.get(0);
        return new ZoneResponse(firstSpaceResponse.getZoneId(), firstSpaceResponse.getZoneName(), seatResponses);
    }

    private SeatResponse getSeatResponse(Set<Long> reservedSeatIds, LayoutQueryResponse spaceResponse) {
        boolean isReserved = reservedSeatIds.contains(spaceResponse.getSeatId());
        return new SeatResponse(spaceResponse.getSeatId(), spaceResponse.getSeatNumber(), isReserved, spaceResponse.getSeatType());
    }

    private Set<Long> getReservedSeatIds(List<LayoutQueryResponse> spaceQueryResponses, DateTimeRange dateTimeRange) {
        List<Long> seatIds = spaceQueryResponses.stream()
                .map(LayoutQueryResponse::getSeatId)
                .collect(Collectors.toList());

        LocalTime startTime = dateTimeRange.getStartTime();
        LocalTime endTime = dateTimeRange.getEndTime();
        return seatReservationDao.findAllBySeatIdInAndReservationDate(seatIds, dateTimeRange.getDate())
                .stream()
                .filter(seatReservation ->
                        (beforeOrEqual(seatReservation.getStartTime(), startTime) && afterOrEqual(seatReservation.getEndTime(), startTime)) ||
                                (afterOrEqual(seatReservation.getStartTime(), endTime) && beforeOrEqual(seatReservation.getEndTime(), endTime)))
                .map(SeatReservation::getSeat)
                .map(Seat::getId)
                .collect(Collectors.toSet());
    }

}
