package com.example.moodyserver.controller;

import com.example.moodyserver.entity.YoutubeRec;
import com.example.moodyserver.service.YoutubeRecService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/youtuberecs")
public class YoutubeRecController {

    private final YoutubeRecService youtubeRecService;

    public YoutubeRecController(YoutubeRecService youtubeRecService) {
        this.youtubeRecService = youtubeRecService;
    }

    @PostMapping
    public ResponseEntity<YoutubeRec> saveYoutubeRec(@RequestBody YoutubeRec youtubeRec) {
        return ResponseEntity.ok(youtubeRecService.save(youtubeRec));
    }

    @GetMapping
    public ResponseEntity<List<YoutubeRec>> getAll() {
        return ResponseEntity.ok(youtubeRecService.findAll());
    }

    @GetMapping("/by-emotion")
    public ResponseEntity<List<YoutubeRec>> getByEmotion(@RequestParam String emotion) {
        return ResponseEntity.ok(youtubeRecService.findByEmotion(emotion));
    }
}