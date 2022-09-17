package com.destroyers.spaceallocation.service;

import com.destroyers.spaceallocation.dao.EmployeeDao;
import com.destroyers.spaceallocation.dao.SeatDao;
import com.destroyers.spaceallocation.dao.SeatReservationDao;
import com.destroyers.spaceallocation.entities.Employee;
import com.destroyers.spaceallocation.entities.Seat;
import com.destroyers.spaceallocation.entities.SeatReservation;
import com.destroyers.spaceallocation.model.DateRange;
import com.destroyers.spaceallocation.model.seat.request.SeatRequest;
import com.destroyers.spaceallocation.model.seat.request.SeatReservationRequest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class SeatServiceTest {

    @InjectMocks
    private SeatService seatService;

    @Mock
    private SeatDao seatDao;

    @Mock
    private SeatReservationDao seatReservationDao;

    @Mock
    private EmployeeDao employeeDao;

    @Nested
    class ReserveSeat {

        @Test
        void shouldReserveSeatIfSeatIsWithinSpaceOfUsersOECode() {
            String pid = "M12345";
            LocalDate startDate = LocalDate.now().plusDays(1);
            LocalDate endDate = LocalDate.now().plusDays(3);
            Seat seat = new Seat(2L, "2", null, "WINDOW");
            Employee employee = mock(Employee.class);
            SeatReservation seatReservation = new SeatReservation(1L, seat, startDate, endDate, employee);

            when(seatDao.findById(2L)).thenReturn(Optional.of(seat));
            when(seatReservationDao.saveAll(any())).thenReturn(List.of(seatReservation));
            when(employeeDao.findByMpid(pid)).thenReturn(Optional.of(employee));

            List<Long> reservationIds = seatService.reserve(new SeatReservationRequest(List.of(new SeatRequest(2L, pid)), List.of(new DateRange(startDate, endDate))));

            assertThat(reservationIds).isEqualTo(List.of(1L));
            verify(seatReservationDao).saveAll(List.of(new SeatReservation(null, seat, startDate, endDate, employee)));
        }

        @Test
        void shouldThrowExceptionIfSeatIsAlreadyReserved() {
            String pid = "M12345";
            Employee employee = mock(Employee.class);
            LocalDate startDate = LocalDate.now().plusDays(1);
            LocalDate endDate = LocalDate.now().plusDays(3);
            Seat seat = new Seat(2L, "2", null, "WINDOW");
            SeatReservation seatReservation = new SeatReservation(1L, seat, startDate, endDate, employee);

            when(seatDao.findById(2L)).thenReturn(Optional.of(seat));
            when(seatReservationDao.getReservationsBetween(2L, startDate, endDate)).thenReturn(List.of(seatReservation));
            when(employeeDao.findByMpid(pid)).thenReturn(Optional.of(employee));

            assertThrows(ResponseStatusException.class,
                    () -> seatService.reserve(new SeatReservationRequest(List.of(new SeatRequest(2L, pid)), List.of(new DateRange(startDate, endDate)))),
                    "Seat already reserved for given dates");
        }

    }

}