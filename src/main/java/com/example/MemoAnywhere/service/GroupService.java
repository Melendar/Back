package com.example.MemoAnywhere.service;


import com.example.MemoAnywhere.domain.Group;
import com.example.MemoAnywhere.dto.GroupDTO;
import com.example.MemoAnywhere.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;

    public GroupDTO getGroupById(Long groupId) {   // 그룹 정보 조회
        System.out.println("id: " + groupId);
        Optional<Group> group = groupRepository.findById(groupId);
        System.out.println("user: " + group.get());
        return group.isPresent() ? GroupDTO.of(group.get()) : null;
    }

    public GroupDTO createGroup(String group_name) {    // 그룹 생성
        Group group = Group.builder()
                .groupName(group_name)
                .build();

        Group savedGroup = groupRepository.save(group);
        return GroupDTO.of(savedGroup);
    }

    public GroupDTO updateGroup(Long groupId, String group_name) {    // 그룹 이름 업데이트
        Optional<Group> group = groupRepository.findById(groupId);
        if (group.isPresent()) {
            Group updatedGroup = group.get();
            updatedGroup.setGroupName(group_name);
            Group savedGroup = groupRepository.save(updatedGroup);
            return GroupDTO.of(savedGroup);
        } else {
            return null;
        }
    }

    public boolean deleteGroupById(Long groupId) {    // 그룹 삭제하기
        Optional<Group> group = groupRepository.findById(groupId);
        if (group.isPresent()) {
            groupRepository.deleteById(groupId);
            return true;
        } else {
            return false; // 그룹이 존재하지 않을 경우 false 반환
        }
    }

    public List<String> getGroupNamesByGroupIds(List<Long> groupIds) {  // group_id 리스트를 받아 group_name 리스트 반환
        return groupRepository.findByGroupIdIn(groupIds)
                .stream()
                .map(Group::getGroupName)  // Group 엔티티에서 group_name을 추출
                .collect(Collectors.toList());  // List<String>으로 변환
    }
}
