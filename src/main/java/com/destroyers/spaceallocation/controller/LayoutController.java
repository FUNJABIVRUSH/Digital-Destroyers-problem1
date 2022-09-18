package com.destroyers.spaceallocation.controller;

import com.destroyers.spaceallocation.model.DateTimeRange;
import com.destroyers.spaceallocation.model.space.response.LayoutResponse;
import com.destroyers.spaceallocation.service.LayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@RestController
@RequestMapping("/layout")
@CrossOrigin(origins = "*")
public class LayoutController {

    @Autowired
    private LayoutService layoutService;

    @GetMapping
    public LayoutResponse getLayout(@RequestParam Long buildingId,
                                    @RequestParam(required = false) String date,
                                    @RequestParam(required = false) String startTime,
                                    @RequestParam(required = false) String endTime,
                                    @RequestParam String pid) {
        if (Objects.isNull(date)) {
            return layoutService.getLayout(buildingId, null);
        }
        DateTimeRange dateTimeRange = new DateTimeRange(LocalDate.parse(date), startTime, endTime);
        return layoutService.getLayout(buildingId, dateTimeRange);
    }
}
