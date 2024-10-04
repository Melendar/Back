package com.example.MemoAnywhere.dto;

import com.example.MemoAnywhere.domain.Group;
import lombok.Data;

@Data
public class GroupDTO {
    private Long groupId;
    private String groupName;

    public static GroupDTO of(Group group) {
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setGroupId(group.getGroupId());
        groupDTO.setGroupName(group.getGroupName());
        return groupDTO;
    }
}