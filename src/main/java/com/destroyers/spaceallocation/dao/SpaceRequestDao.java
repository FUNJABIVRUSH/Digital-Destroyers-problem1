package com.destroyers.spaceallocation.dao;

import com.destroyers.spaceallocation.entities.SpaceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceRequestDao extends JpaRepository<SpaceRequest, Long> {
}
