package com.example.Melendar.dto;

import com.example.Melendar.domain.Group;
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

    public Long getId(){
        return groupId;
    }
}