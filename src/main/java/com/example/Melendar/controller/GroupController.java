package com.example.Melendar.controller;

import com.example.Melendar.dto.GroupDTO;
import com.example.Melendar.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    // 1. 그룹 불러오기:   특정 userId에 매핑되는 모든 group 반환
    @GetMapping("/getByUser")
    public List<Map<String, Object>> getGroupsByUserId(@RequestParam Long userId) {
        List<GroupDTO> groups = groupService.getGroupsByUserId(userId);

        // 각 그룹마다 사용자 목록을 조회하여 Map에 포함
        return groups.stream().map(groupDTO -> {
            Map<String, Object> result = new HashMap<>();
            result.put("group", groupDTO);
            result.put("users", groupService.getUsersInGroup(groupDTO.getGroupId())); // 사용자 목록 추가
            return result;
        }).collect(Collectors.toList());
    }

    // 2. 그룹 추가하기: 새로운 그룹 생성
    @PostMapping("/create")
    public GroupDTO createGroup(@RequestParam Long userId, @RequestBody GroupDTO groupDTO) {
        return groupService.createGroup(userId, groupDTO);
    }

    // 3. 그룹 나가기
    @DeleteMapping("/leave")
    public void leaveGroup(@RequestParam Long userId, @RequestParam Long groupId) {
        groupService.leaveGroup(userId, groupId);
    }

    // 4. 그룹 수정하기: 특정 groupId에 해당하는 그룹 수정
    @PutMapping("/update/{groupId}")
    public GroupDTO updateGroup(@PathVariable Long groupId, @RequestBody GroupDTO groupDTO) {
        return groupService.updateGroup(groupId, groupDTO);
    }

    // 5. 그룹원 초대
    @PostMapping("/invite")
    public void inviteUserToGroup(@RequestParam Long groupId, @RequestParam Long userId) {
        groupService.inviteUserToGroup(groupId, userId);
    }
}