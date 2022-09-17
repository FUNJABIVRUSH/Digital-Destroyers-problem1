package com.destroyers.spaceallocation.dao;

import com.destroyers.spaceallocation.entities.OECode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OECodeDao extends JpaRepository<OECode, Long> {
    List<OECode> findByDepartmentId(Long departmentId);

    @Query(value = "select * from oe_code where parent_oe_code_id = :parentOeCodeId", nativeQuery = true)
    List<OECode> findAllByParentOECodeId(Long parentOeCodeId);
}
