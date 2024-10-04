package com.example.MemoAnywhere.controller;

import com.example.MemoAnywhere.dto.GroupDTO;
import com.example.MemoAnywhere.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    // 1. 그룹 정보 조회
    @GetMapping("/{groupId}")
    public GroupDTO getGroupById(@PathVariable Long groupId) {
        return groupService.getGroupById(groupId);
    }

    // 2. 그룹 생성
    @PostMapping("/create")
    public GroupDTO createGroup(@RequestParam String groupName) {
        return groupService.createGroup(groupName);
    }

    // 3. 그룹 이름 업데이트
    @PutMapping("/update/{groupId}")
    public GroupDTO updateGroup(@PathVariable Long groupId, @RequestParam String groupName) {
        return groupService.updateGroup(groupId, groupName);
    }

    // 4. 그룹 삭제
    @DeleteMapping("/delete/{groupId}")
    public boolean deleteGroup(@PathVariable Long groupId) {
        return groupService.deleteGroupById(groupId);
    }

    // 5. 여러 그룹 이름 조회 - groupIds 리스트를 받아 그룹 이름 리스트 반환
    @PostMapping("/names")
    public List<String> getGroupNamesByGroupIds(@RequestBody List<Long> groupIds) {
        return groupService.getGroupNamesByGroupIds(groupIds);
    }
}
