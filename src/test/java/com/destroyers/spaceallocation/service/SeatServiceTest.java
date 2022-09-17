package com.destroyers.spaceallocation.service;

import com.destroyers.spaceallocation.dao.SeatDao;
import com.destroyers.spaceallocation.dao.SeatReservationDao;
import com.destroyers.spaceallocation.entities.Seat;
import com.destroyers.spaceallocation.entities.SeatReservation;
import com.destroyers.spaceallocation.model.DateRange;
import com.destroyers.spaceallocation.model.seat.request.SeatReservationRequest;
import com.destroyers.spaceallocation.model.space.response.SpaceResponse;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class SeatServiceTest {

    @InjectMocks
    private SeatService seatService;

    @Mock
    private SpaceService spaceService;

    @Mock
    private SeatDao seatDao;

    @Mock
    private SeatReservationDao seatReservationDao;

    @Nested
    class ReserveSeat {

        @Test
        void shouldReserveSeatIfSeatIsWithinSpaceOfUsersOECode() {
            String pid = "M12345";
            LocalDate startDate = LocalDate.now().plusDays(1);
            LocalDate endDate = LocalDate.now().plusDays(3);
            Seat seat = new Seat(2L, "2", null, "WINDOW", false);
            SeatReservation seatReservation = new SeatReservation(1L, seat, startDate, endDate);

            SpaceResponse spaceResponse = new SpaceResponse(1L, 1L, 1L, 10L, LocalDate.now(),LocalDate.now().plusDays(5));

            when(spaceService.getSpaceAllocatedTo(pid)).thenReturn(List.of(spaceResponse));
            when(seatDao.findById(2L)).thenReturn(Optional.of(seat));
            when(seatReservationDao.saveAll(any())).thenReturn(List.of(seatReservation));

            List<Long> reservationIds = seatService.reserve(pid, new SeatReservationRequest(2L, List.of(new DateRange(startDate, endDate))));

            assertThat(reservationIds).isEqualTo(List.of(1L));
            verify(seatReservationDao).saveAll(List.of(new SeatReservation(null, seat, startDate, endDate)));
        }

        @Test
        void shouldThrowExceptionIfSeatIsAlreadyReserved() {
            String pid = "M12345";
            LocalDate startDate = LocalDate.now().plusDays(1);
            LocalDate endDate = LocalDate.now().plusDays(3);
            Seat seat = new Seat(2L, "2", null, "WINDOW", false);
            SeatReservation seatReservation = new SeatReservation(1L, seat, startDate, endDate);
            SpaceResponse spaceResponse = new SpaceResponse(1L, 1L, 1L, 10L);

            when(spaceService.getSpaceAllocatedTo(pid)).thenReturn(List.of(spaceResponse));
            when(seatDao.findById(2L)).thenReturn(Optional.of(seat));
            when(seatReservationDao.getReservationsBetween(2L, startDate, endDate)).thenReturn(List.of(seatReservation));

            assertThrows(ResponseStatusException.class,
                    () -> seatService.reserve(pid, new SeatReservationRequest(2L, List.of(new DateRange(startDate, endDate)))),
                    "Seat already reserved for given dates");
        }

    }

}