package com.destroyers.spaceallocation.service;

import com.destroyers.spaceallocation.dao.CustomNativeQueryExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatService {

    @Autowired
    CustomNativeQueryExecutor customNativeQueryExecutor;

    public Integer getSeatCapacityPercent(Long buildingId){
        Double totalSeats = customNativeQueryExecutor.getTotalSeats(buildingId);
        Double totalEmployees = customNativeQueryExecutor.getTotalEmployees(buildingId);
        return (int)((totalSeats/totalEmployees) * 100);
    }
}
