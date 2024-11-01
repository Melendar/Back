package com.example.Melendar.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@Table(name = "User")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = true, length = 50)  // nullable, 50자 제한 (데이터베이스와 일치)
    private String nickname;

    @Column(name = "profile_image", length = 30)  // 데이터베이스의 profile_image와 매핑
    private String profileImage;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Memo> memos;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserGroup> userGroups;

}