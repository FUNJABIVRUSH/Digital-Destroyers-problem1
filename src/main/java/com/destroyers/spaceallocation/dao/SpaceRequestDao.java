package com.destroyers.spaceallocation.dao;

import com.destroyers.spaceallocation.entities.OECode;
import com.destroyers.spaceallocation.entities.SpaceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpaceRequestDao extends JpaRepository<SpaceRequest, Long> {
    List<SpaceRequest> getAllByRequestOeCodeId(OECode oeCode);
    List<SpaceRequest> getAllByOwnerOeCodeId(OECode oeCode);
}
