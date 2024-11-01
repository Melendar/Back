package com.example.Melendar.dto;

import com.example.Melendar.domain.User;
import lombok.Data;

@Data
public class UserDTO {
    private Long userId;
    private String nickname;
    private String profileImage;

    public static UserDTO of(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setNickname(user.getNickname());
        userDTO.setProfileImage(user.getProfileImage());
        return userDTO;
    }
}