package com.Healthify_AIService.service;

import com.Healthify_AIService.model.Recommendation;
import com.Healthify_AIService.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecommendationService {


    private RecommendationRepository recommendationRepository;

    public List<Recommendation> getUserRecommendation(String userId) {
        return recommendationRepository.findByUserId(userId);
    }

    public Recommendation getActivityRecommendation(String activityId) {
        try {
            System.out.println(activityId);
            return recommendationRepository.findByActivityId(activityId)

                    .orElseThrow(() -> new RuntimeException("No recommendation found for this activity: " + activityId));

        } catch (Exception e) {
            e.printStackTrace();
            throw  e;// prints full stack trace in console
        }

    }
}