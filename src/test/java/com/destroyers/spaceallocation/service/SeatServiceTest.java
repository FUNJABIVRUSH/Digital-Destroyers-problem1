package com.destroyers.spaceallocation.service;

import com.destroyers.spaceallocation.dao.SeatDao;
import com.destroyers.spaceallocation.entities.Seat;
import com.destroyers.spaceallocation.model.seat.request.SeatReservationRequest;
import com.destroyers.spaceallocation.model.space.response.SpaceResponse;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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

    @Nested
    class ReserveSeat {

        @Test
        void shouldReserveSeatIfSeatIsWithinSpaceOfUsersOECode() {
            String pid = "M12345";
            Seat reservedSeat = new Seat(2L, "2", null, "WINDOW", true);
            SpaceResponse spaceResponse = new SpaceResponse(1L, 1L, 1L, 10L, LocalDate.now(),LocalDate.now().plusDays(5));

            when(spaceService.getSpaceAllocatedTo(pid)).thenReturn(List.of(spaceResponse));
            when(seatDao.findById(2L)).thenReturn(Optional.of(new Seat(2L, "2", null, "WINDOW", false)));
            when(seatDao.save(any())).thenReturn(reservedSeat);

            Long seatId = seatService.reserve(pid, new SeatReservationRequest(2L));

            assertThat(seatId).isEqualTo(2L);
            verify(seatDao).save(reservedSeat);
        }

    }

}