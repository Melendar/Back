package com.example.MemoAnywhere.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity // 해당 클래스가 JPA 엔티티임을 명시
@Getter
@Setter
@Builder
@Table(name = "User")   // 해당 엔티티가 User 테이블에 매핑
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id // 해당 필드를 기본키로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 값이 증가
    @Column(name = "user_id")   // 데이터베이스의 user_id 컬럼과 매핑
    private Long userId;

    @Column(nullable = false, length = 50)  // nullable, 50자 제한
    private String nickname;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CalendarEvent> calendarEvents;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Memo> memos;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserGroup> userGroups;
    // mappedBy 속성 값은 테이블 이름과 직접적 연관 x, 관계 정의하는 반대쪽 엔티티에서 참조하는 필드의 이름을 가리킴
}
