package com.destroyers.spaceallocation.service;

import com.destroyers.spaceallocation.dao.*;
import com.destroyers.spaceallocation.entities.*;
import com.destroyers.spaceallocation.model.employee.EmployeeRole;
import com.destroyers.spaceallocation.model.space.AllocateSpaceRequest;
import com.destroyers.spaceallocation.model.space.FloorRequest;
import com.destroyers.spaceallocation.model.space.response.SpaceResponse;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpaceServiceTest {

    @InjectMocks
    private SpaceService spaceService;

    @Mock
    private SpaceDao spaceDao;

    @Mock
    private EmployeeDao employeeDao;

    @Mock
    private SeatDao seatDao;

    @Mock
    private SeatRangeDao seatRangeDao;

    @Mock
    private OECodeDao oeCodeDao;

    @Nested
    class AllocateNewSpaceTest {

        @Test
        void shouldAllocateSpace() {
            String pid = "M12345";
            Employee employee = mock(Employee.class);
            Seat startSeat = mock(Seat.class);
            Seat endSeat = mock(Seat.class);
            OECode oeCode = mock(OECode.class);
            SeatRange seatRange = new SeatRange(null, startSeat, endSeat);

            when(employeeDao.findByMpid(pid)).thenReturn(Optional.of(employee));
            when(seatDao.findAllById(List.of(1L, 10L))).thenReturn(List.of(startSeat, endSeat));
            when(seatRangeDao.save(seatRange)).thenReturn(seatRange);
            when(oeCodeDao.findById(1L)).thenReturn(Optional.of(oeCode));
            when(spaceDao.saveAll(any())).thenReturn(List.of(new Space(2L, seatRange, employee, oeCode, LocalDate.now(),LocalDate.now().plusDays(5L))));

            FloorRequest floorRequest = new FloorRequest(1L, 10L);
            var allocateSpaceRequest = new AllocateSpaceRequest(1L, LocalDate.now(),LocalDate.now().plusDays(5L),List.of(floorRequest));

            List<Long> spaceIds = spaceService.allocate(pid, allocateSpaceRequest);

            assertThat(spaceIds).isEqualTo(List.of(2L));
        }
    }

    @Nested
    class AllocatedSpaceTest {

        @Test
        void shouldReturnSpaceAssignedToEmployee() {
            String pid = "M12345";
            Employee employee = mock(Employee.class);
            OECode oeCode = new OECode(1L, "MBLD1", 100, null, "LOW", null);
            Floor floor = new Floor(1L, "1", new Building(1L, "EON2"));
            Zone zone = new Zone(1L, "A", floor);
            Seat startSeat = new Seat(1L, "1", zone, "WINDOW");
            Seat endSeat = new Seat(10L, "10", zone, "NON_WINDOW");

            SeatRange seatRange = new SeatRange(1L, startSeat, endSeat);

            when(employeeDao.findByMpid(pid)).thenReturn(Optional.of(employee));
            when(employee.getOeCode()).thenReturn(oeCode);
            when(spaceDao.findAllByAssignedOeCodeId(any())).thenReturn(List.of(new Space(1L,seatRange, employee, oeCode, LocalDate.now(),LocalDate.now().plusDays(5L))));

            List<SpaceResponse> spaceResponses = spaceService.getSpaceAllocatedTo(pid);

            assertThat(spaceResponses).isEqualTo(List.of(new SpaceResponse(1L, 1L, 1L, 10L,
                    LocalDate.now(),LocalDate.now().plusDays(5L))));
        }
    }

    @Nested
    class ReservedSpaceTest {

        @Test
        void shouldReturnSpaceReservedByEmployee() {
            String pid = "M12345";

            OECode oeCode = new OECode(1L, "MBLD1", 100, null, "LOW", null);
            Employee employee = new Employee(1L,pid,"XYZ", EmployeeRole.EMPLOYEE,null,oeCode);
            Floor floor = new Floor(1L, "1", new Building(1L, "EON2") );
            Zone zone = new Zone(1L, "A", floor);
            Seat startSeat = new Seat(1L, "1", zone, "WINDOW");
            Seat endSeat = new Seat(10L, "10", zone, "NON_WINDOW");

            SeatRange seatRange = new SeatRange(1L, startSeat, endSeat);

            when(employeeDao.findByMpid(pid)).thenReturn(Optional.of(employee));
            when(spaceDao.findAllByCreatedEmployeeId(any())).thenReturn(List.of(new Space(1L,seatRange, employee, oeCode, LocalDate.now(),LocalDate.now().plusDays(5L))));

            List<SpaceResponse> spaceResponses = spaceService.getSpaceReservedBy(pid);

            assertThat(spaceResponses).isEqualTo(List.of(new SpaceResponse( 1L, 1L, 1L, 10L,
                    LocalDate.now(),LocalDate.now().plusDays(5L))));
        }
    }

    @Nested
    class DeleteSpaceTest {

        @Test
        void shouldDeleteSpaceByOECode() {
            when(oeCodeDao.findById(any())).thenReturn(Optional.of(new OECode(1L,"MBLD",500,null,null)));

            spaceService.deleteSpace(1L, Collections.emptyList());

            verify(spaceDao).deleteByAssignedOeCodeId(any());
        }

        @Test
        void shouldNotCallDBIfOECodeIsNull() {
            spaceService.deleteSpace(null, Collections.emptyList());
            verify(spaceDao,times(0)).deleteByAssignedOeCodeId(any());
        }

        @Test
        void shouldDeleteSpaceBySpaceIds() {
            spaceService.deleteSpace(null, List.of(1L,2L));
            verify(spaceDao).deleteAllById(List.of(1L,2L));
            verify(oeCodeDao,times(0)).findById(any());
        }
    }
}