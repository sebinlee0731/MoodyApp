package com.example.moodyserver.controller;

import com.example.moodyserver.entity.PlaceRec;
import com.example.moodyserver.service.PlaceRecService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/placerecs")
public class PlaceRecController {

    private final PlaceRecService placeRecService;

    public PlaceRecController(PlaceRecService placeRecService) {
        this.placeRecService = placeRecService;
    }

    // 전체 장소 추천 조회
    @GetMapping
    public ResponseEntity<List<PlaceRec>> getAllPlaceRecs() {
        return ResponseEntity.ok(placeRecService.getAll());
    }

    // 감정 기반 장소 추천 조회
    @GetMapping("/by-emotion")
    public ResponseEntity<List<PlaceRec>> getByEmotion(@RequestParam String emotion) {
        return ResponseEntity.ok(placeRecService.getRecommendationsByEmotion(emotion));
    }

    // 장소 추천 저장
    @PostMapping
    public ResponseEntity<PlaceRec> savePlaceRec(@RequestBody PlaceRec placeRec) {
        return ResponseEntity.ok(placeRecService.save(placeRec));
    }
}