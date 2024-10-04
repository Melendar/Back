package com.example.MemoAnywhere.domain;

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
    @JoinColumn(name = "group_id", nullable = false)
    private Long group_id;
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
    private Boolean repeat;

    @Column(name = "is_completed", nullable = false)
    private Boolean isCompleted;
}