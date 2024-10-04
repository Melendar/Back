package com.example.MemoAnywhere.service;


import com.example.MemoAnywhere.domain.User;
import com.example.MemoAnywhere.dto.UserDTO;
import com.example.MemoAnywhere.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDTO getUserById(Long id) {   // 사용자 정보 조회, 존재하는 사용자면 반환
        System.out.println("id: " + id);
        Optional<User> user = userRepository.findById(id);
        System.out.println("user: " + user.get());
        return user.isPresent() ? UserDTO.of(user.get()) : null;
    }

    public UserDTO createUser(String userNickname) {    // 사용자 생성
        User user = User.builder()
                .nickname(userNickname)
                .build();

        User savedUser = userRepository.save(user);
        return UserDTO.of(savedUser);
    }

    public UserDTO updateUser(Long userId, String userNickname) {    // user의 nickname 업데이트
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User updatedUser = user.get();
            updatedUser.setNickname(userNickname);
            User savedUser = userRepository.save(updatedUser);
            return UserDTO.of(savedUser);
        } else {
            return null;
        }
    }

    public List<String> getNicknamesByUserIds(List<Long> userIds) { // user_id 리스트에 매핑되는 nickname 리스트 반환
        return userRepository.findByUserIdIn(userIds)
                .stream()
                .map(User::getNickname)  // User 엔티티에서 nickname을 추출
                .collect(Collectors.toList());  // List<String>으로 변환
    }
}


