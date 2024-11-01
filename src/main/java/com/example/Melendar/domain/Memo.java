package com.example.Melendar.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "Memo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memo_id")
    private Long memoId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Lob
    @Column(nullable = true)  // text 타입에 맞게 @Lob 사용
    private String title;

    @Lob
    @Column(nullable = true)  // text 타입에 맞게 @Lob 사용
    private String content;

    @Column(nullable = true)
    private LocalDate date;
}