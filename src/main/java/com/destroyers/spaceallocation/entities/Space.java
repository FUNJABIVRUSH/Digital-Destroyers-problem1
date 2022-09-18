package com.destroyers.spaceallocation.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "space")
@Entity
public class Space {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "range_id", referencedColumnName = "id")
    private SeatRange range;

    @OneToOne
    @JoinColumn(name = "created_employee_id", referencedColumnName = "id")
    private Employee createdEmployeeId;

    @OneToOne
    @JoinColumn(name = "assigned_oe_code_id", referencedColumnName = "id")
    private OECode assignedOeCodeId;

    @Column(name = "start_date", columnDefinition = "DATE")
    private LocalDate startDate;

    @Column(name = "end_date", columnDefinition = "DATE")
    private LocalDate endDate;

    @Column(name = "is_confirmed")
    private Boolean isConfirmed;
}
