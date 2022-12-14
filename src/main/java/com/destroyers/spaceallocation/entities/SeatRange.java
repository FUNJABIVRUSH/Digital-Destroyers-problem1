package com.destroyers.spaceallocation.entities;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "seat_range")
@Entity
@EqualsAndHashCode
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

}
