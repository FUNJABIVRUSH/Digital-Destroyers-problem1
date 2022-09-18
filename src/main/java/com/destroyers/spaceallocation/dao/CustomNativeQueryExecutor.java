package com.destroyers.spaceallocation.dao;

import com.destroyers.spaceallocation.model.space.LayoutQueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigInteger;
import java.util.List;

@Repository
public class CustomNativeQueryExecutor {

    private final EntityManager entityManager;

    @Autowired
    public CustomNativeQueryExecutor(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<LayoutQueryResponse> getLayoutByBuildingId(Long buildingId) {
        return entityManager.createNativeQuery("select building.name as buildingName, floor.id as floorId, floor.name as floorName, zone.id as zoneId, zone.name as zoneName, seat.id as seatId, seat.number as seatNumber, seat.type as seatType" +
                        "                        from building join floor on building.id = floor.building_id join zone on zone.floor_id = floor.id join seat on seat.zone_id = zone.id " +
                        "                       where building.id = ?1","LayoutQuery")
                .setParameter(1, buildingId)
                .getResultList();
    }

    public  Double getTotalSeats(Long buildingId) {
        return ((BigInteger) entityManager.createNativeQuery( "select count(seat.id) as totalSeat" +
                "                        from building join floor on building.id = floor.building_id join zone on zone.floor_id = floor.id join seat on seat.zone_id = zone.id " +
                "                       where building.id = ?1")
                .setParameter(1,buildingId)
                .getSingleResult()).doubleValue();
    }

    public  Double getTotalEmployees(Long buildingId) {
        return ((BigInteger) entityManager.createNativeQuery( "select sum(oe_code.total_employees) as totalEmp" +
                        "                        from building join department on building.id = department.building_id join oe_code on oe_code.department_id = department.id "+
                        "                       where building.id = ?1 and oe_code.parent_oe_code_id is NULL")
                .setParameter(1,buildingId)
                .getSingleResult()).doubleValue();
    }

}
