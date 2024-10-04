package com.example.Melendar.service;

import com.example.Melendar.domain.UserGroup;
import com.example.Melendar.dto.UserGroupDTO;
import com.example.Melendar.repository.UserGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserGroupService {
    private final UserGroupRepository userGroupRepository;

    public UserGroupDTO createUG(String group_color, String group_description) {    // 생성
         UserGroup ug = UserGroup.builder()
                .groupColor(group_color)
                .groupDescription(group_description)
                .build();

        UserGroup savedUserGroup = userGroupRepository.save(ug);
        return UserGroupDTO.of(savedUserGroup);
    }

    public UserGroupDTO updateUsergroup(Long user_group_id, String group_color, String group_description) {     // 업데이트
        Optional<UserGroup> ug = userGroupRepository.findById(user_group_id);
        if (ug.isPresent()) {
            UserGroup updatedUsergroup = ug.get();
            updatedUsergroup.setGroupColor(group_color);
            updatedUsergroup.setGroupDescription(group_description);
            UserGroup savedUG = userGroupRepository.save(updatedUsergroup);
            return UserGroupDTO.of(savedUG);
        } else {
            return null;
        }
    }

    public UserGroupDTO getUsergroupById(Long ug_Id) {   //  정보 가져오기
        System.out.println("id: " + ug_Id);
        Optional<UserGroup> ug = userGroupRepository.findById(ug_Id);
        System.out.println("user: " + ug.get());
        return ug.isPresent() ? UserGroupDTO.of(ug.get()) : null;
    }

    public boolean deleteUsergroupById(Long ug_Id) {    // 삭제하기
        Optional<UserGroup> ug = userGroupRepository.findById(ug_Id);
        if (ug.isPresent()) {
            userGroupRepository.deleteById(ug_Id);
            return true;
        } else {
            return false;
        }
    }

    // 같은 그룹에 속해있는 user_id 리스트 반환
    public List<Long> getUserIdsByGroupId(Long groupId) {
        // groupId로 UserGroup 목록을 조회한 후, 각 UserGroup에서 user_id를 추출하여 리스트로 반환
        return userGroupRepository.findByGroup_GroupId(groupId)
                .stream()
                .map(userGroup -> userGroup.getUser().getUserId())  // user_id 대신 User 객체에서 ID 추출
                .collect(Collectors.toList());
    }

    // 한 user가 속해있는 모든 group_id 리스트 반환
    public List<Long> getGroupIdsByUserId(Long userId) {
        return userGroupRepository.findByUser_Id(userId)
                .stream()
                .map(userGroup -> userGroup.getGroup().getGroupId())  // group_id 대신 Group 객체에서 ID 추출
                .collect(Collectors.toList());
    }

}
