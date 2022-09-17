package com.destroyers.spaceallocation.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "seat")
@Entity
@EqualsAndHashCode
@SuperBuilder(toBuilder = true)
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    @ManyToOne
    @JoinColumn(name = "zone_id", referencedColumnName = "id")
    private Zone zone;

    private String type;
    @Column(name = "is_reserved")
    private Boolean isReserved;
}
