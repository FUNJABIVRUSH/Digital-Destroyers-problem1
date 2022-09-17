package com.destroyers.spaceallocation.service;

import com.destroyers.spaceallocation.dao.*;
import com.destroyers.spaceallocation.entities.*;
import com.destroyers.spaceallocation.model.space.AllocateSpaceRequest;
import com.destroyers.spaceallocation.model.space.FloorRequest;
import com.destroyers.spaceallocation.model.space.response.SpaceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpaceService {

    private final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private SpaceDao spaceDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private SeatDao seatDao;

    @Autowired
    private SeatRangeDao seatRangeDao;

    @Autowired
    private OECodeDao oeCodeDao;

    public List<SpaceResponse> getSpaceAllocatedTo(Long buildingId, String pid) {
        Employee employee = getEmployee(pid);
        OECode oeCode = employee.getOeCode();
        return seatRangeDao.findAllByOeCodeId(oeCode.getId()).stream()
                .map(seatRange -> SpaceResponse.from(buildingId, seatRange))
                .collect(Collectors.toList());
    }

    public List<Long> allocate(String pid, AllocateSpaceRequest allocateSpaceRequest) {
        Employee employee = getEmployee(pid);

        List<Space> spaces = createSpaces(employee, allocateSpaceRequest);

        return spaceDao.saveAll(spaces)
                .stream()
                .map(Space::getId)
                .collect(Collectors.toList());
    }

    private List<Space> createSpaces(Employee employee, AllocateSpaceRequest allocateSpaceRequest) {
        List<FloorRequest> floorRequests = allocateSpaceRequest.getFloorRequests();
        Long oeCodeId = allocateSpaceRequest.getOeCodeId();
        return floorRequests.stream()
                .map(floorRequest -> {
                    List<Seat> seats = seatDao.findAllById(List.of(floorRequest.getStartSeatId(), floorRequest.getEndSeatId()));
                    OECode oeCode = getOeCode(oeCodeId);
                    SeatRange seatRange = new SeatRange(null, seats.get(0), seats.get(1), employee, oeCode);
                    SeatRange savedSeatRange = seatRangeDao.save(seatRange);
                    return new Space(null, savedSeatRange);
                })
                .collect(Collectors.toList());
    }

    private OECode getOeCode(Long oeCodeId) {
        return oeCodeDao.findById(oeCodeId)
                .orElseThrow(() -> {
                    LOGGER.error("OeCodeId is not valid {}", oeCodeId);
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "OeCodeId is not valid " + oeCodeId);
                });

    }

    private Employee getEmployee(String pid) {
        return employeeDao.findByMpid(pid).orElseThrow(() -> {
            LOGGER.error("Employee not found. pid: {}", pid);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found for pid " + pid);
        });
    }

    public List<SpaceResponse> getSpaceReservedBy(String pid) {
        Employee employee = getEmployee(pid);
        return seatRangeDao.findAllByEmployeeId(employee.getId()).stream()
                .map(seatRange -> {
                    Long buildingId = seatRange.getStartSeat().getZone().getFloor().getBuilding().getId();
                   return SpaceResponse.from(buildingId, seatRange);
                })
                .collect(Collectors.toList());
    }
}
