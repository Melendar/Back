package com.example.MemoAnywhere.controller;

import com.example.MemoAnywhere.dto.UserDTO;
import com.example.MemoAnywhere.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user") //이 클래스 내의 요청은 /api/user/~~ 식으로됨
public class UserController {
    @Autowired      // 의존성 주입
    private UserService userService;

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/create")
    public UserDTO createUser(@RequestParam("nick_name") String userNickname) {
        return userService.createUser(userNickname);
    }

    @PostMapping("/update")
    public UserDTO updateUser(@RequestParam("id") Long userId,
                              @RequestParam("nick_name") String userNickname) {
        return userService.updateUser(userId, userNickname);
    }

    @PostMapping("/nicknames")
    public List<String> getNicknamesByUserIds(@RequestBody List<Long> userIds) {
        return userService.getNicknamesByUserIds(userIds);
    }
}
