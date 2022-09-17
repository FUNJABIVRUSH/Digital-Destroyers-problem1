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
@Table(name = "oe_code")
@Entity
public class OECode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(name = "total_employees")
    private Integer totalEmployees;

    @ManyToOne()
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;
    private String type;

    @OneToOne
    @JoinColumn(name = "parent_oe_code_id", referencedColumnName = "id")
    private OECode parentOECode;
}
