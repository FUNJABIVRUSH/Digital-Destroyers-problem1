package com.destroyers.spaceallocation.service;

import com.destroyers.spaceallocation.dao.EmployeeDao;
import com.destroyers.spaceallocation.dao.SeatDao;
import com.destroyers.spaceallocation.dao.SeatRangeDao;
import com.destroyers.spaceallocation.dao.SpaceDao;
import com.destroyers.spaceallocation.entities.Employee;
import com.destroyers.spaceallocation.entities.Seat;
import com.destroyers.spaceallocation.entities.SeatRange;
import com.destroyers.spaceallocation.entities.Space;
import com.destroyers.spaceallocation.model.space.AllocateSpaceRequest;
import com.destroyers.spaceallocation.model.space.FloorRequest;
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


    public List<Long> allocate(String pid, AllocateSpaceRequest allocateSpaceRequest) {
        Employee employee = employeeDao.findByMpid(pid).orElseThrow(() -> {
            LOGGER.error("Employee not found. pid: {}", pid);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found for pid " + pid);
        });

        List<FloorRequest> floorRequests = allocateSpaceRequest.getFloorRequests();
        List<Space> spaces = createSpaces(employee, floorRequests);

        return spaceDao.saveAll(spaces)
                .stream()
                .map(Space::getId)
                .collect(Collectors.toList());
    }

    private List<Space> createSpaces(Employee employee, List<FloorRequest> floorRequests) {
        return floorRequests.stream()
                .map(floorRequest -> {
                    List<Seat> seats = seatDao.findAllById(List.of(floorRequest.getStartSeatId(), floorRequest.getEndSeatId()));
                    SeatRange seatRange = new SeatRange(null, seats.get(0), seats.get(1), employee);
                    SeatRange savedSeatRange = seatRangeDao.save(seatRange);
                    return new Space(null, savedSeatRange);
                })
                .collect(Collectors.toList());
    }
}
