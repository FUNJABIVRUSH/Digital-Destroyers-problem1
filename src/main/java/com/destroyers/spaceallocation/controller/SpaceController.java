package com.destroyers.spaceallocation.controller;

import com.destroyers.spaceallocation.model.space.AllocateSpaceRequest;
import com.destroyers.spaceallocation.model.space.response.SpaceResponse;
import com.destroyers.spaceallocation.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/space")
@CrossOrigin(origins = "*")
public class SpaceController {

    @Autowired
    private SpaceService spaceService;
    @GetMapping
    public SpaceResponse getSpaceInfo(@RequestParam(required = false) String spaceId,
                                      @RequestParam Long buildingId,
                                      @RequestParam String pid) {
        return spaceService.getSpaceDetails(buildingId);
    }

    @PostMapping("/allocate")
    public ResponseEntity<String> allocateSpace(@RequestBody AllocateSpaceRequest allocateSpaceRequest,
                                                @RequestParam String pid) throws URISyntaxException {
        return ResponseEntity.created(new URI("")).build();
    }
}
