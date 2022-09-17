package com.destroyers.spaceallocation.controller;

import com.destroyers.spaceallocation.model.space.response.LayoutResponse;
import com.destroyers.spaceallocation.service.LayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/layout")
@CrossOrigin(origins = "*")
public class LayoutController {

    @Autowired
    private LayoutService layoutService;
    @GetMapping
    public LayoutResponse getLayout(@RequestParam Long buildingId,
                                    @RequestParam String pid) {
        return layoutService.getLayout(buildingId);
    }
}
