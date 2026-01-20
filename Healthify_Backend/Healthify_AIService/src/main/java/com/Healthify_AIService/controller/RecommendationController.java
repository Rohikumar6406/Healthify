package com.Healthify_AIService.controller;


import com.Healthify_AIService.model.Recommendation;
import com.Healthify_AIService.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recommendations")
public class RecommendationController {

    @Autowired
    private final RecommendationService recommendationService;


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Recommendation>>getUserRecommendation(@PathVariable String userId ){
        return ResponseEntity.ok(recommendationService.getUserRecommendation(userId));
    }

    @GetMapping("/activity/{activityId}")
    public ResponseEntity<Recommendation> getActivityRecommendation(@PathVariable String activityId){
        return ResponseEntity.ok(recommendationService.getActivityRecommendation(activityId));
    }
}
