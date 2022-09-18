package com.destroyers.spaceallocation.service;

import com.destroyers.spaceallocation.dao.CustomNativeQueryExecutor;
import com.destroyers.spaceallocation.model.seat.SeatCapacityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatService {

    @Autowired
    CustomNativeQueryExecutor customNativeQueryExecutor;

    public SeatCapacityResponse getSeatCapacity(Long buildingId, String pid){
        Double totalSeats = customNativeQueryExecutor.getTotalSeats(buildingId);
        Double totalEmployees = customNativeQueryExecutor.getTotalEmployees(buildingId);
        double maxPercent = (totalSeats/totalEmployees) * 100;

        return new SeatCapacityResponse((int) maxPercent);

    }
}
