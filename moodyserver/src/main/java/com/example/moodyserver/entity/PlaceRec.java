package com.example.moodyserver.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "placerecs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceRec {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;     // 장소 이름

    private String location; // 주소 또는 위치 설명

    private String reason;   // 추천 이유

    private String emotion;  // 감정 태그

    @CreationTimestamp
    private Date createdAt;
}
