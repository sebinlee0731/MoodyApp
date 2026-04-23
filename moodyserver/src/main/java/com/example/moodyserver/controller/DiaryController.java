package com.example.moodyserver.controller;

import com.example.moodyserver.dto.*;
import com.example.moodyserver.entity.Diary;
import com.example.moodyserver.service.DiaryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diaries")
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping
    public ResponseEntity<Diary> saveDiary(@RequestBody DiaryRequestDTO request) {
        Diary saved = diaryService.save(request);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/by-user/{userName}")
    public ResponseEntity<List<DiaryResponse>> getDiariesByUser(@PathVariable String userName) {
        List<DiaryResponse> result = diaryService.findByUserName(userName);
        return ResponseEntity.ok(result);
    }
}