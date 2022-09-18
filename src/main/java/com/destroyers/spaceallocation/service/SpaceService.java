package com.destroyers.spaceallocation.service;

import com.destroyers.spaceallocation.dao.*;
import com.destroyers.spaceallocation.entities.*;
import com.destroyers.spaceallocation.model.space.AllocateSpaceRequest;
import com.destroyers.spaceallocation.model.space.EditSpaceRequest;
import com.destroyers.spaceallocation.model.space.FloorRequest;
import com.destroyers.spaceallocation.model.space.response.SpaceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
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

    @Autowired
    private SpaceRequestDao spaceRequestDao;

    public List<SpaceResponse> getSpaceAllocatedTo(String pid) {
        Employee employee = getEmployee(pid);
        OECode oeCode = employee.getOeCode();
        return spaceDao.findAllByAssignedOeCodeIdAndIsConfirmed(oeCode, true)
                .stream()
                .map(SpaceResponse::from)
                .collect(Collectors.toList());
    }


    @Transactional
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
                    SeatRange seatRange = getSeatRange(floorRequest);
                    SeatRange savedSeatRange = seatRangeDao.save(seatRange);
                    return new Space(null, savedSeatRange, employee, getOeCode(oeCodeId), allocateSpaceRequest.getStartDate(), allocateSpaceRequest.getEndDate(), true);
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

    private Space getSpace(Long spaceId) {
        return spaceDao.findById(spaceId).orElseThrow(() -> {
            LOGGER.error("Space not found. id: {}", spaceId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Space not found. id: " + spaceId);
        });
    }

    private Seat getSeat(Long seatId) {
        return seatDao.findById(seatId).orElseThrow(() -> {
            LOGGER.error("Seat not found. id: {}", seatId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Seat not found. id: " + seatId);
        });
    }

    public List<SpaceResponse> getSpaceReservedBy(String pid) {
        Employee employee = getEmployee(pid);
        return spaceDao.findAllByCreatedEmployeeId(employee).stream()
                .map(SpaceResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteSpace(Long oeCode, List<Long> spaceIds, String pid) {
        Employee employee = getEmployee(pid);
        if (Objects.nonNull(oeCode)) {
            OECode oeCodeEntity = getOeCode(oeCode);
            if (employee.getOeCode().getId().equals(oeCode)) {
                spaceDao.deleteByAssignedOeCodeId(oeCodeEntity);
                return;
            }
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        if (Objects.nonNull(spaceIds)) {
            spaceDao.findAllById(spaceIds)
                    .stream()
                    .filter(space -> employee.getOeCode().equals(space.getAssignedOeCodeId()))
                    .forEach(space -> spaceDao.delete(space));
        }
    }

    @Transactional
    public SpaceResponse editSpace(EditSpaceRequest request, Long spaceId, String pid) {
        Employee employee = getEmployee(pid);
        Space space = getSpace(spaceId);
        if (space.getCreatedEmployeeId().equals(employee)) {
            space.setAssignedOeCodeId(getOeCode(request.getOeCodeId()));
            final SeatRange range = space.getRange();
            range.setEndSeat(getSeat(request.getEndSeatId()));
            range.setStartSeat(getSeat(request.getStartSeatId()));
            return SpaceResponse.from(spaceDao.saveAndFlush(space));
        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }

    @Transactional
    public List<Long> requestSpace(AllocateSpaceRequest request, String pid) {
        Employee employee = getEmployee(pid);
        List<Space> spaces = createSpaceRequests(employee, request);
        return spaceDao.saveAll(spaces).stream()
                .map(space -> createSpaceRequest(request, employee, space))
                .collect(Collectors.toList());
    }

    private Long createSpaceRequest(AllocateSpaceRequest request, Employee employee, Space space) {
        SpaceRequest spaceRequest = new SpaceRequest(null, space, getOeCode(request.getOeCodeId()),
                employee.getOeCode(), false, LocalDate.now(), null);
        return spaceRequestDao.save(spaceRequest).getId();
    }

    private List<Space> createSpaceRequests(Employee employee, AllocateSpaceRequest allocateSpaceRequest) {
        List<FloorRequest> floorRequests = allocateSpaceRequest.getFloorRequests();
        return floorRequests.stream()
                .map(floorRequest -> {
                    SeatRange seatRange = getSeatRange(floorRequest);
                    SeatRange savedSeatRange = seatRangeDao.save(seatRange);
                    return new Space(null, savedSeatRange, employee, null, allocateSpaceRequest.getStartDate(), allocateSpaceRequest.getEndDate(), false);
                })
                .collect(Collectors.toList());
    }

    private SeatRange getSeatRange(FloorRequest floorRequest) {
        List<Seat> seats = seatDao.findAllById(List.of(floorRequest.getStartSeatId(), floorRequest.getEndSeatId()));
        if (seats.size() == 2) {
            return new SeatRange(null, seats.get(0), seats.get(1));
        }
        return new SeatRange(null, seats.get(0), seats.get(0));
    }
}

