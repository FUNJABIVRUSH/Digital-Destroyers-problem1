package com.destroyers.spaceallocation.controller;

import com.destroyers.spaceallocation.model.space.AllocateSpaceRequest;
import com.destroyers.spaceallocation.model.space.response.SpaceResponse;
import com.destroyers.spaceallocation.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/space")
@CrossOrigin(origins = "*")
public class SpaceController {

    @Autowired
    private SpaceService spaceService;

    @PostMapping("/allocate")
    public List<Long> allocateSpace(@RequestBody AllocateSpaceRequest allocateSpaceRequest,
                                    @RequestParam String pid) {
        return spaceService.allocate(pid, allocateSpaceRequest);
    }

    @GetMapping("/allocated")
    public List<SpaceResponse> getAllocatedSpace(@RequestParam String pid){
        return spaceService.getSpaceAllocatedTo(pid);
    }

    @GetMapping("/reserved")
    public List<SpaceResponse> getSpaceReservedBy(@RequestParam String pid){
        return spaceService.getSpaceReservedBy(pid);
    }
}
