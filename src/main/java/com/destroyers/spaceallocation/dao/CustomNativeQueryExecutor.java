package com.destroyers.spaceallocation.dao;

import com.destroyers.spaceallocation.model.space.LayoutQueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CustomNativeQueryExecutor {

    private final EntityManager entityManager;

    @Autowired
    public CustomNativeQueryExecutor(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<LayoutQueryResponse> getLayoutByBuildingId(Long buildingId) {
        return entityManager.createNativeQuery("select building.name as buildingName, floor.id as floorId, floor.name as floorName, zone.id as zoneId, zone.name as zoneName, seat.id as seatId, seat.number as seatNumber, seat.type as seatType, seat.is_reserved as isReserved " +
                        "                        from building join floor on building.id = floor.building_id join zone on zone.floor_id = floor.id join seat on seat.zone_id = zone.id " +
                        "                       where building.id = ?1","LayoutQuery")
                .setParameter(1, buildingId)
                .getResultList();
    }
}
