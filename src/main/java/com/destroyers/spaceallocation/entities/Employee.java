package com.destroyers.spaceallocation.entities;


import com.destroyers.spaceallocation.model.employee.EmployeeRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mpid;
    private String name;
    @Enumerated(EnumType.STRING)
    private EmployeeRole role;


}