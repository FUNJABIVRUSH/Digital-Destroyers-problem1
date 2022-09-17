package com.destroyers.spaceallocation.controller;

import com.destroyers.spaceallocation.model.DateRange;
import com.destroyers.spaceallocation.model.space.response.LayoutResponse;
import com.destroyers.spaceallocation.service.LayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Objects;

@RestController
@RequestMapping("/layout")
@CrossOrigin(origins = "*")
public class LayoutController {

    @Autowired
    private LayoutService layoutService;
    @GetMapping
    public LayoutResponse getLayout(@RequestParam Long buildingId,
                                    @RequestParam(required = false) String startDate,
                                    @RequestParam(required = false) String endDate,
                                    @RequestParam String pid) {
        if(Objects.isNull(startDate) || Objects.isNull(endDate)){
            return layoutService.getLayout(buildingId, null);
        }
        DateRange dateRange = new DateRange(LocalDate.parse(startDate), LocalDate.parse(endDate));
        return layoutService.getLayout(buildingId, dateRange);
    }
}
