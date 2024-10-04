package com.example.MemoAnywhere.dto;

import com.example.MemoAnywhere.domain.User;
import lombok.Data;

@Data
public class UserDTO {
    private Long userId;
    private String nickname;

    public static UserDTO of(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setNickname(user.getNickname());
        return userDTO;
    }
}