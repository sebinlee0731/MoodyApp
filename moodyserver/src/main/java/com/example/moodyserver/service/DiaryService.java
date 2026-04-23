package com.example.moodyserver.service;

import com.example.moodyserver.dto.*;
import com.example.moodyserver.entity.Diary;
import com.example.moodyserver.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public Diary save(DiaryRequestDTO req) {
        LocalDateTime writtenAt = (req.getWrittenAt() != null)
                ? req.getWrittenAt()
                : LocalDateTime.now();    // 없으면 현재 시각 사용

        Diary diary = Diary.builder()
                .userName(req.getUserName())
                .content(req.getContent())
                .writtenAt(writtenAt)
                .emotion(req.getEmotion())
                .build();

        return diaryRepository.save(diary);
    }

    public List<DiaryResponse> findByUserName(String userName) {
        return diaryRepository.findByUserName(userName).stream()
                .map(diary -> new DiaryResponse(
                        diary.getWrittenAt().toLocalDate().toString(),  // 날짜만 추출
                        diary.getEmotion(),
                        diary.getContent()
                ))
                .collect(Collectors.toList());
    }
}