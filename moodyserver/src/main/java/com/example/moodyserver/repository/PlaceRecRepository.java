package com.example.moodyserver.repository;

import com.example.moodyserver.entity.PlaceRec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRecRepository extends JpaRepository<PlaceRec, Long> {
    List<PlaceRec> findByEmotion(String emotion);
}