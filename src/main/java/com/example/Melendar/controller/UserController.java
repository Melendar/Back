package com.example.Melendar.controller;

import com.example.Melendar.dto.UserDTO;
import com.example.Melendar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user") //이 클래스 내의 요청은 /api/user/~~ 식으로됨
public class UserController {
    @Autowired      // 의존성 주입
    private UserService userService;

    @GetMapping("/login")
    public UserDTO login(@RequestParam Long userId) {
        // 서비스에서 로그인 로직 처리 후 UserDTO 반환
        return userService.login(userId);
    }

    // 닉네임 업데이트
    @PutMapping("/updateNickname")
    public UserDTO updateNickname(@RequestParam Long userId, @RequestParam String newNickname) {
        return userService.updateNickname(userId, newNickname);
    }

    // 프로필 이미지 업데이트
    @PutMapping("/updateProfileImage")
    public UserDTO updateProfileImage(@RequestParam Long userId, @RequestParam String newProfileImage) {
        return userService.updateProfileImage(userId, newProfileImage);
    }

}
