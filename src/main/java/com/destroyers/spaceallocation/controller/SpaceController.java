package com.destroyers.spaceallocation.controller;

import com.destroyers.spaceallocation.model.space.AllocateSpaceRequest;
import com.destroyers.spaceallocation.model.space.SpaceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/space")
public class SpaceController {

    @GetMapping("{spaceId}")
    public SpaceResponse getSpaceInfo(@PathVariable(required = false) String spaceId
            , @RequestParam("pid") String pid) {
        return null;
    }

    @PostMapping("/allocate")
    public ResponseEntity<String> allocateSpace(@RequestBody AllocateSpaceRequest allocateSpaceRequest,
                                                @RequestParam String pid) throws URISyntaxException {
        return ResponseEntity.created(new URI("")).build();
    }
}
