// src/main/java/com/example/moodyserver/dto/DiaryRequest.java
package com.example.moodyserver.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DiaryRequestDTO {
    private String userName;
    private String content;
    private String emotion;

    // JSON이 ISO 포맷("yyyy-MM-dd'T'HH:mm:ss")으로 들어올 때 자동 파싱
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime writtenAt;
}