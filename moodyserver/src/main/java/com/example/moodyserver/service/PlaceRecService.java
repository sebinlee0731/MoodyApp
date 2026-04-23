package com.example.moodyserver.service;

import com.example.moodyserver.entity.PlaceRec;
import com.example.moodyserver.repository.PlaceRecRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceRecService {

    private final PlaceRecRepository placeRecRepository;

    public PlaceRecService(PlaceRecRepository placeRecRepository) {
        this.placeRecRepository = placeRecRepository;
    }

    // 장소 추천 저장
    public PlaceRec save(PlaceRec placeRec) {
        return placeRecRepository.save(placeRec);
    }

    // 모든 장소 추천 조회
    public List<PlaceRec> getAll() {
        return placeRecRepository.findAll();
    }

    // 감정 기반 장소 추천 조회
    public List<PlaceRec> getRecommendationsByEmotion(String emotion) {
        return placeRecRepository.findByEmotion(emotion);
    }
}