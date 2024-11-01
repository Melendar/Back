package com.example.Melendar.dto;

import com.example.Melendar.domain.Group;
import lombok.Data;

@Data
public class GroupDTO {
    private Long groupId;
    private String groupName;
    private String groupColor;
    private String groupDescription;

    public static GroupDTO of(Group group) {
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setGroupId(group.getGroupId());
        groupDTO.setGroupName(group.getGroupName());
        groupDTO.setGroupDescription(group.getGroupDescription());
        groupDTO.setGroupColor(group.getGroupColor());
        return groupDTO;
    }
}