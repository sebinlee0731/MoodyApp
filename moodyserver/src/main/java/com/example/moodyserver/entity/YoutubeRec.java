package com.example.moodyserver.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "youtuberecs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class YoutubeRec {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title; // 영상 제목

    private String url; // 영상 URL

    private String reason; // 추천 이유

    private String emotion; // 감정 태그

    @CreationTimestamp
    private Date createdAt;
}