package com.website.analytics_system.controller;

import com.website.analytics_system.repository.ViewLogRepository;
import com.website.analytics_system.repository.ErrorLogRepository;
import com.website.analytics_system.repository.ActiveSessionRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AnalyticsController {

    private final ViewLogRepository viewLogRepository;
    private final ErrorLogRepository errorLogRepository;
    private final ActiveSessionRepository activeSessionRepository;

    public AnalyticsController(ViewLogRepository viewLogRepository,
                               ErrorLogRepository errorLogRepository,
                               ActiveSessionRepository activeSessionRepository) {
        this.viewLogRepository = viewLogRepository;
        this.errorLogRepository = errorLogRepository;
        this.activeSessionRepository = activeSessionRepository;
    }

    @GetMapping("/getAnalytics")
    public Map<String, Object> getAnalytics() {
        Map<String, Object> analytics = new HashMap<>();

        // Count views for each page
        analytics.put("home page views", viewLogRepository.countByPageName("home"));
        analytics.put("about page views", viewLogRepository.countByPageName("about"));
        analytics.put("contact page views", viewLogRepository.countByPageName("contact"));

        // Active users count (sessions currently alive)
        analytics.put("active users", activeSessionRepository.count());

        // Errors count
        analytics.put("errors", errorLogRepository.count());

        return analytics;
    }
}
