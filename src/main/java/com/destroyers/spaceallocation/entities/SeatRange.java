package com.destroyers.spaceallocation.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "seat_range")
@Entity
public class SeatRange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "start_seat_id", referencedColumnName = "id")
    private Seat startSeat;
    @OneToOne
    @JoinColumn(name = "end_seat_id", referencedColumnName = "id")
    private Seat endSeat;
    @OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;
}
