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
@Table(name = "space_request")
@Entity
public class SpaceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "request_space_id", referencedColumnName = "id")
    private Space requestSpaceId;

    @OneToOne
    @JoinColumn(name = "owner_oe_code_id", referencedColumnName = "id")
    private OECode ownerOeCodeId;

    @OneToOne
    @JoinColumn(name = "request_oe_code_id", referencedColumnName = "id")
    private OECode requestOeCodeId;

    @Column(name = "is_approved")
    private Boolean isApproved;

    @Column(name = "created_date", columnDefinition = "DATE")
    private LocalDate createdDate;

    @Column(name = "approval_date", columnDefinition = "DATE")
    private LocalDate approvalDate;
}
