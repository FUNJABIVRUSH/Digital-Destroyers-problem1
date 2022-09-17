package com.destroyers.spaceallocation.controller;

import com.destroyers.spaceallocation.model.space.AllocateSpaceRequest;
import com.destroyers.spaceallocation.model.space.response.LayoutResponse;
import com.destroyers.spaceallocation.service.LayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/layout")
@CrossOrigin(origins = "*")
public class LayoutController {

    @Autowired
    private LayoutService layoutService;
    @GetMapping
    public LayoutResponse getLayout(@RequestParam(required = false) String spaceId,
                                       @RequestParam Long buildingId,
                                       @RequestParam String pid) {
        return layoutService.getLayout(buildingId);
    }

    @PostMapping("/allocate")
    public ResponseEntity<String> allocateSpace(@RequestBody AllocateSpaceRequest allocateSpaceRequest,
                                                @RequestParam String pid) throws URISyntaxException {
        return ResponseEntity.created(new URI("")).build();
    }
}
