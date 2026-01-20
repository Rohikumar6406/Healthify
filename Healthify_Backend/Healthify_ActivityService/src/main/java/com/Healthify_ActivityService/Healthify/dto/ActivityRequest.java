package com.Healthify_ActivityService.Healthify.dto;

import com.Healthify_ActivityService.Healthify.model.ActivityType;
import lombok.Data;


import java.time.LocalDateTime;
import java.util.Map;

@Data
public class ActivityRequest {
    private String userId;
    private ActivityType type;
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startTime;
    private Map<String, Object> additionalMetrics;


}

