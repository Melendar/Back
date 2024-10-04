package com.example.MemoAnywhere.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "UserGroup")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_group_id")
    private Long userGroupId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Long user_id;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Long group_id;

    @Column(name = "group_color", length = 7) // 색상 코드(예: #FFFFFF)를 위한 필드
    private String groupColor;

    @Column(name = "group_description", length = 255)
    private String groupDescription;
}