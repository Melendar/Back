package com.example.Melendar.service;


import com.example.Melendar.domain.User;
import com.example.Melendar.dto.UserDTO;
import com.example.Melendar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // 로그인 기능
    public UserDTO login(Long userId) {
        // 1. 해당 id의 User가 있는지 조회
        Optional<User> userOptional = userRepository.findById(userId);

        // 2. 만약 존재하면, User 정보를 DTO로 변환하여 반환
        if (userOptional.isPresent()) {
            return UserDTO.of(userOptional.get());
        }

        // 3. 존재하지 않는다면, 새로운 User 생성 후 저장
        User newUser = User.builder()
                .userId(userId)
                .nickname("New User")  // 기본 닉네임 설정 (예시)
                .profileImage("default.jpg")  // 기본 이미지 설정 (예시)
                .build();

        User savedUser = userRepository.save(newUser);

        // 4. 생성된 User 정보를 DTO로 변환하여 반환
        return UserDTO.of(savedUser);
    }

    // 닉네임 업데이트
    public UserDTO updateNickname(Long userId, String newNickname) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setNickname(newNickname);
            userRepository.save(user);
            return UserDTO.of(user);
        } else {
            throw new IllegalArgumentException("User not found with id: " + userId);
        }
    }

    // 프로필 이미지 업데이트
    public UserDTO updateProfileImage(Long userId, String newProfileImage) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setProfileImage(newProfileImage);
            userRepository.save(user);
            return UserDTO.of(user);
        } else {
            throw new IllegalArgumentException("User not found with id: " + userId);
        }
    }
}


