package com.destroyers.spaceallocation.controller;

import com.destroyers.spaceallocation.model.space.AllocateSpaceRequest;
import com.destroyers.spaceallocation.model.space.response.SpaceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/space")
@CrossOrigin(origins = "*")
public class SpaceController {

    @GetMapping
    public SpaceResponse getSpaceInfo(@RequestParam(required = false) String spaceId,
                                      @RequestParam String buildingId,
                                      @RequestParam String pid) {
        return null;
    }

    @PostMapping("/allocate")
    public ResponseEntity<String> allocateSpace(@RequestBody AllocateSpaceRequest allocateSpaceRequest,
                                                @RequestParam String pid) throws URISyntaxException {
        return ResponseEntity.created(new URI("")).build();
    }
}
