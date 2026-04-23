package com.example.moodyserver.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "diaries")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Diary {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diaryId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime writtenAt;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private String emotion;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}