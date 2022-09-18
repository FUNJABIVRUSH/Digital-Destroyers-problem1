package com.destroyers.spaceallocation.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "seat_reservation")
@Entity
@EqualsAndHashCode
public class SeatReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "seat_id", referencedColumnName = "id")
    private Seat seat;

    @Column(name = "reservation_date", columnDefinition = "DATE")
    private LocalDate reservationDate;

    @Column(name = "start_time", columnDefinition = "TIME")
    private LocalTime startTime;

    @Column(name = "end_time", columnDefinition = "TIME")
    private LocalTime endTime;

    @OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

}
