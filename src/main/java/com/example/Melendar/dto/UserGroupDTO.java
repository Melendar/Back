package com.example.Melendar.dto;

import com.example.Melendar.domain.UserGroup;
import lombok.Data;

@Data
public class UserGroupDTO {
    private Long userGroupId;
    private Long userId;
    private Long groupId;
    private String groupColor;
    private String groupDescription;

    public static UserGroupDTO of(UserGroup userGroup) {
        UserGroupDTO usergroupDTO = new UserGroupDTO();
        usergroupDTO.setUserGroupId(userGroup.getUserGroupId());
        usergroupDTO.setUserId(userGroup.getUser().getUserId());
        usergroupDTO.setGroupId(userGroup.getGroup().getGroupId());
        usergroupDTO.setGroupColor(userGroup.getGroupColor());
        usergroupDTO.setGroupDescription(userGroup.getGroupDescription());
        return usergroupDTO;
    }
}