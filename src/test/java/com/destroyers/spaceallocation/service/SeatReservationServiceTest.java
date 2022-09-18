package com.destroyers.spaceallocation.service;

import com.destroyers.spaceallocation.dao.EmployeeDao;
import com.destroyers.spaceallocation.dao.SeatDao;
import com.destroyers.spaceallocation.dao.SeatReservationDao;
import com.destroyers.spaceallocation.entities.Employee;
import com.destroyers.spaceallocation.entities.Seat;
import com.destroyers.spaceallocation.entities.SeatReservation;
import com.destroyers.spaceallocation.model.DateTimeRange;
import com.destroyers.spaceallocation.model.seat.request.DeleteReservationRequest;
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
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class SeatReservationServiceTest {

    @InjectMocks
    private SeatReservationService seatReservationService;

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
            LocalDate reservationDate = LocalDate.now().plusDays(1);
            LocalTime startTime = LocalTime.now().plusHours(1);
            LocalTime endTime = LocalTime.now().plusMinutes(9);

            Seat seat = new Seat(2L, "2", null, "WINDOW");
            Employee employee = mock(Employee.class);
            SeatReservation seatReservation = new SeatReservation(1L, seat, reservationDate, startTime, endTime, employee);

            when(seatDao.findById(2L)).thenReturn(Optional.of(seat));
            when(seatReservationDao.saveAll(any())).thenReturn(List.of(seatReservation));
            when(employeeDao.findByMpid(pid)).thenReturn(Optional.of(employee));

            List<Long> reservationIds = seatReservationService.reserve(new SeatReservationRequest(List.of(new SeatRequest(2L, pid, List.of(new DateTimeRange(reservationDate, startTime.toString(), endTime.toString()))))));

            assertThat(reservationIds).isEqualTo(List.of(1L));
            verify(seatReservationDao).saveAll(List.of(new SeatReservation(null, seat, reservationDate, startTime, endTime, employee)));
        }

        @Test
        void shouldThrowExceptionIfSeatIsAlreadyReserved() {
            String pid = "M12345";
            Employee employee = mock(Employee.class);
            LocalDate reservationDate = LocalDate.now().plusDays(1);
            LocalTime startTime = LocalTime.now().plusHours(1);
            LocalTime endTime = LocalTime.now().plusMinutes(9);
            Seat seat = new Seat(2L, "2", null, "WINDOW");
            SeatReservation seatReservation = new SeatReservation(1L, seat, reservationDate, startTime, endTime, employee);

            when(seatDao.findById(2L)).thenReturn(Optional.of(seat));
            when(seatReservationDao.findAllBySeatIdInAndReservationDate(List.of(2L), reservationDate)).thenReturn(List.of(seatReservation));
            when(employeeDao.findByMpid(pid)).thenReturn(Optional.of(employee));

            assertThrows(ResponseStatusException.class,
                    () -> seatReservationService.reserve(new SeatReservationRequest(List.of(new SeatRequest(2L, pid, List.of(new DateTimeRange(reservationDate, startTime.toString(), endTime.toString())))))),
                    "Seat already reserved for given date & time");
        }

    }

    @Nested
    class DeleteReservation {

        @Test
        void shouldDeleteReservationForGivenPid() {
            String pid = "M12345";
            LocalDate date = LocalDate.now();
            DeleteReservationRequest reservationRequest = new DeleteReservationRequest(pid, date);
            Employee employee = mock(Employee.class);
            SeatReservation seatReservation = mock(SeatReservation.class);

            when(employeeDao.findByMpid(pid)).thenReturn(Optional.of(employee));
            when(employee.getId()).thenReturn(1L);
            when(seatReservationDao.findAllByEmployeeIdAndReservationDate(1L, date)).thenReturn(List.of(seatReservation));

            seatReservationService.deleteReservations(List.of(reservationRequest));

            verify(seatReservationDao).deleteAll(List.of(seatReservation));
        }
    }

}