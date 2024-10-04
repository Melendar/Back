package com.example.Melendar.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "CalendarEvent")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalendarEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long eventId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)  // 외래 키로 user_id 설정
    private User user;  // User와 다대일 관계

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;
    // JoinColumn은 JPA에서 외래키를 명시적으로 설정하기 위해 사용됨

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false, length = 100)
    private String task;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(nullable = false)
    private Boolean notification;

    @Column(nullable = false)
    private Boolean isRepeated;

    @Column(name = "is_completed", nullable = false)
    private Boolean isCompleted;
}