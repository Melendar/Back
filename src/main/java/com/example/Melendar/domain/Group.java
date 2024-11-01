package com.example.Melendar.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "`Group`")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "group_name", nullable = true, length = 100)  // 데이터베이스와 일치시키기 위해 nullable = true로 수정
    private String groupName;

    @Column(name = "group_color", length = 7) // 색상 코드(예: #FFFFFF)를 위한 필드
    private String groupColor;

    @Column(name = "group_description", length = 255)
    private String groupDescription;

    // Group과 UserGroup 사이의 일대다 관계
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserGroup> userGroups;

    // Group과 CalendarEvent 사이의 일대다 관계
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CalendarEvent> calendarEvents;
}