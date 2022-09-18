package com.destroyers.spaceallocation.controller;

import com.destroyers.spaceallocation.model.space.AllocateSpaceRequest;
import com.destroyers.spaceallocation.model.space.EditSpaceRequest;
import com.destroyers.spaceallocation.model.space.response.SpaceRequestResponseWrapper;
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
    public List<SpaceResponse> getAllocatedSpace(@RequestParam String pid) {
        return spaceService.getSpaceAllocatedTo(pid);
    }

    @GetMapping("/reserved")
    public List<SpaceResponse> getSpaceReservedBy(@RequestParam String pid) {
        return spaceService.getSpaceReservedBy(pid);
    }

    @DeleteMapping
    public void deleteSpace(@RequestParam(required = false) Long oeCodeId,
                            @RequestParam(required = false) List<Long> spaceIds,
                            @RequestParam String pid) {
        spaceService.deleteSpace(oeCodeId, spaceIds, pid);
    }

    @PutMapping("/{spaceId}")
    public SpaceResponse editSpace(@RequestBody EditSpaceRequest request,
                                   @PathVariable Long spaceId,
                                   @RequestParam String pid) {
        return spaceService.editSpace(request, spaceId, pid);
    }

    @PostMapping("/request")
    public List<Long> requestSpace(@RequestBody AllocateSpaceRequest request,
                                   @RequestParam String pid) {
        return spaceService.requestSpace(request, pid);
    }

    @GetMapping("/request")
    public SpaceRequestResponseWrapper getRequestedSpaces(@RequestParam String pid) {
        return spaceService.getRequestedSpace(pid);
    }

    @PutMapping("/request/approve")
    public void approveRequest(@RequestParam Long requestId,@RequestParam String pid) {
         spaceService.approveRequest(requestId,pid);
    }
}
