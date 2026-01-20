package com.Healthify_AIService.service;

import com.Healthify_AIService.model.Activity;
import com.Healthify_AIService.model.Recommendation;
import com.Healthify_AIService.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityMessageListener {

    private final RecommendationRepository recommendationRepository;

    private final ActivityAIService activityAIService;


//    @KafkaListener(topics = "${kafka.topic.name}", groupId = "activity-processor-group")
//    public void processActivity(Activity activity){
//        log.info("Received Activity for processing: activityId={}, userId={}", activity, activity.getUserId());
//        Recommendation recommendation = activityAIService.generateRecommendation(activity);
//        recommendationRepository.save(recommendation);
//
//    }

    //    @KafkaListener(topics = "${kafka.topic.name}", groupId = "activity-processor-group")
//    public void processActivity(Activity activity){
//        log.info("Received Activity for processing: {}", activity.getUserId());
//
//        Recommendation recommendation = activityAIService.generateRecommendation(activity);
//
//        // Attach activityId to recommendation
//        recommendation.setActivityId(activity.getActivityId());
//
//        recommendationRepository.save(recommendation);
//        log.info("Saved recommendation for activityId={}", activity.getActivityId());
//    }
    @KafkaListener(topics = "${kafka.topic.name}", groupId = "activity-processor-group")
    public void processActivity(Activity activity) {
        try {
            log.info("✅ Received Activity for processing: userId={}, activityId={}",
                    activity.getUserId(), activity.getId());

            // Validate
            if (activity.getId() == null) {
                log.error("❌ Activity ID is null");
                return;
            }

            // Check if already processed (idempotency)
            if (recommendationRepository.existsByActivityId(activity.getId())) {
                log.warn("⚠️ Recommendation already exists for activityId: {}", activity.getId());
                return;
            }

            // Generate recommendation
            Recommendation recommendation = activityAIService.generateRecommendation(activity);

            // Use getId() instead of getActivityId()
            recommendation.setActivityId(activity.getId());
            recommendation.setUserId(activity.getUserId());

            Recommendation saved = recommendationRepository.save(recommendation);
            log.info("✅ Saved recommendation for activityId={}", saved.getActivityId());

        } catch (Exception e) {
            log.error("❌ Failed to process activity: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to process activity", e);
        }

    }
}
