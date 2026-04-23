package com.example.moodyserver.service;

import com.example.moodyserver.entity.YoutubeRec;
import com.example.moodyserver.repository.YoutubeRecRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YoutubeRecService {

    private final YoutubeRecRepository youtubeRecRepository;

    public YoutubeRecService(YoutubeRecRepository youtubeRecRepository) {
        this.youtubeRecRepository = youtubeRecRepository;
    }

    public YoutubeRec save(YoutubeRec rec) {
        return youtubeRecRepository.save(rec);
    }

    public List<YoutubeRec> findAll() {
        return youtubeRecRepository.findAll();
    }

    public List<YoutubeRec> findByEmotion(String emotion) {
        return youtubeRecRepository.findByEmotion(emotion);
    }
}