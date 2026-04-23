package com.example.moodyserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiaryResponse {
    private String date;     // LocalDateTime -> String으로 처리
    private String emotion;
    private String content;
}