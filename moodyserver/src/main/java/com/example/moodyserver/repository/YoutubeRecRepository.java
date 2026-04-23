package com.example.moodyserver.repository;

import com.example.moodyserver.entity.YoutubeRec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface YoutubeRecRepository extends JpaRepository<YoutubeRec, Long> {
    List<YoutubeRec> findByEmotion(String emotion);
}