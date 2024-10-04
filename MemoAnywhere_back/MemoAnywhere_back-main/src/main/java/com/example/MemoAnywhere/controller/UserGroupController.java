package com.example.MemoAnywhere.controller;

import com.example.MemoAnywhere.dto.UserGroupDTO;
import com.example.MemoAnywhere.service.UserGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-groups")
@RequiredArgsConstructor
public class UserGroupController {

    private final UserGroupService userGroupService;

    // 1. UserGroup 생성
    @PostMapping("/create")
    public UserGroupDTO createUserGroup(@RequestParam String groupColor,
                                        @RequestParam String groupDescription) {
        return userGroupService.createUG(groupColor, groupDescription);
    }

    // 2. UserGroup 업데이트
    @PutMapping("/update/{userGroupId}")
    public UserGroupDTO updateUserGroup(@PathVariable Long userGroupId,
                                        @RequestParam String groupColor,
                                        @RequestParam String groupDescription) {
        return userGroupService.updateUsergroup(userGroupId, groupColor, groupDescription);
    }

    // 3. 특정 UserGroup 정보 조회
    @GetMapping("/{userGroupId}")
    public UserGroupDTO getUserGroupById(@PathVariable Long userGroupId) {
        return userGroupService.getUsergroupById(userGroupId);
    }

    // 4. UserGroup 삭제
    @DeleteMapping("/delete/{userGroupId}")
    public boolean deleteUserGroup(@PathVariable Long userGroupId) {
        return userGroupService.deleteUsergroupById(userGroupId);
    }

    // 5. 같은 그룹에 속해있는 user_id 리스트 반환
    @GetMapping("/group/{groupId}/users")
    public List<Long> getUserIdsByGroupId(@PathVariable Long groupId) {
        return userGroupService.getUserIdsByGroupId(groupId);
    }

    // 6. 한 user가 속해있는 모든 group_id 값 리스트 반환
    @GetMapping("/user/{userId}/groups")
    public List<Long> getGroupIdsByUserId(@PathVariable Long userId) {
        return userGroupService.getGroupIdsByUserId(userId);
    }
}
