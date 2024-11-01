package com.example.Melendar.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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
    @JoinColumn(name = "group_id", nullable = false)  // 외래 키로 group_id 설정
    private Group group;  // Group과 다대일 관계

    @Column(nullable = false)
    private LocalDate date;

    @Lob
    @Column(nullable = true)  // text 타입에 맞게 @Lob 사용
    private String task;
}