package com.example.Melendar.service;

import com.example.Melendar.domain.Group;
import com.example.Melendar.domain.User;
import com.example.Melendar.domain.UserGroup;
import com.example.Melendar.dto.GroupDTO;
import com.example.Melendar.dto.UserDTO;
import com.example.Melendar.repository.GroupRepository;
import com.example.Melendar.repository.UserGroupRepository;
import com.example.Melendar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final UserGroupRepository userGroupRepository;

    // 1. 특정 userId에 매핑되는 모든 group을 불러오기
    @Transactional
    public List<GroupDTO> getGroupsByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        return user.getUserGroups().stream()
                .map(userGroup -> GroupDTO.of(userGroup.getGroup()))
                .collect(Collectors.toList());
    }

    // 2. 그룹 생성 및 UserGroup 관계 생성
    @Transactional
    public GroupDTO createGroup(Long userId, GroupDTO groupDTO) {
        // 그룹 생성
        Group group = Group.builder()
                .groupName(groupDTO.getGroupName())
                .groupDescription(groupDTO.getGroupDescription())
                .groupColor(groupDTO.getGroupColor())
                .build();
        Group savedGroup = groupRepository.save(group);

        // UserGroup 관계 생성
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        UserGroup userGroup = UserGroup.builder()
                .user(user)
                .group(savedGroup)
                .build();
        userGroupRepository.save(userGroup);

        return GroupDTO.of(savedGroup);
    }

    // 2. 그룹 나가기
    @Transactional
    public void leaveGroup(Long userId, Long groupId) {
        // UserGroup 관계 삭제
        UserGroup userGroup = userGroupRepository.findByUserUserIdAndGroupGroupId(userId, groupId)
                .orElseThrow(() -> new IllegalArgumentException("UserGroup relationship not found."));
        userGroupRepository.delete(userGroup);

        // 남은 그룹 멤버 확인 후 그룹 삭제
        if (userGroupRepository.countByGroupGroupId(groupId) == 0) {
            groupRepository.deleteById(groupId);
        }
    }

    // 3. 특정 groupId에 해당하는 그룹 수정 (userId는 변경 불가)
    public GroupDTO updateGroup(Long groupId, GroupDTO groupDTO) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Group not found with id: " + groupId));

        group.setGroupName(groupDTO.getGroupName());
        group.setGroupDescription(groupDTO.getGroupDescription());
        group.setGroupColor(groupDTO.getGroupColor());
        groupRepository.save(group);

        return GroupDTO.of(group);
    }

    // 4. 특정 groupId에 해당하는 그룹 삭제
    public void deleteGroup(Long groupId) {
        if (groupRepository.existsById(groupId)) {
            groupRepository.deleteById(groupId);
        } else {
            throw new IllegalArgumentException("Group not found with id: " + groupId);
        }
    }

    // 4. 그룹원 초대
    @Transactional
    public void inviteUserToGroup(Long groupId, Long userId) {
        // 유효한 사용자 확인
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Group not found with id: " + groupId));

        // 사용자가 이미 그룹에 속해 있는지 확인
        if (userGroupRepository.existsByUserAndGroup(user, group)) {
            throw new IllegalArgumentException("User is already a member of the group.");
        }

        // UserGroup 관계 생성
        UserGroup userGroup = UserGroup.builder()
                .user(user)
                .group(group)
                .build();
        userGroupRepository.save(userGroup);
    }

    // 5. 특정 그룹에 속한 모든 사용자 목록을 UserDTO로 반환
    public List<UserDTO> getUsersInGroup(Long groupId) {
        // groupId에 속한 UserGroup 관계를 통해 User 목록을 가져와서 UserDTO로 변환
        return userGroupRepository.findByGroupGroupId(groupId).stream()
                .map(userGroup -> UserDTO.of(userGroup.getUser()))  // User를 UserDTO로 변환
                .collect(Collectors.toList());
    }
}
