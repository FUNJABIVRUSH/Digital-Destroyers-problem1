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
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    @Nested
    class AllocateTest {

        @Test
        void shouldAllocateSpace() {
            String pid = "M12345";
            Employee employee = mock(Employee.class);
            Seat startSeat = mock(Seat.class);
            Seat endSeat = mock(Seat.class);
            SeatRange seatRange = new SeatRange(null, startSeat, endSeat, employee);

            when(employeeDao.findByMpid(pid)).thenReturn(Optional.of(employee));
            when(seatDao.findAllById(List.of(1L, 10L))).thenReturn(List.of(startSeat, endSeat));
            when(seatRangeDao.save(seatRange)).thenReturn(seatRange);
            when(spaceDao.saveAll(any())).thenReturn(List.of(new Space(2L, seatRange)));

            FloorRequest floorRequest = new FloorRequest(1L, 1L, 1L, 10L);
            var allocateSpaceRequest = new AllocateSpaceRequest(List.of(floorRequest));

            List<Long> spaceIds = spaceService.allocate(pid, allocateSpaceRequest);

            assertThat(spaceIds).isEqualTo(List.of(2L));
        }
    }

}