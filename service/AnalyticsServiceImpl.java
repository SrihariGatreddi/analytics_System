package com.website.analytics_system.service;

import com.website.analytics_system.repository.ActiveSessionRepository;
import com.website.analytics_system.repository.ErrorLogRepository;
import com.website.analytics_system.repository.ViewLogRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {

    private final ViewLogRepository viewLogRepository;
    private final ErrorLogRepository errorLogRepository;
    private final ActiveSessionRepository activeSessionRepository;

    public AnalyticsServiceImpl(ViewLogRepository viewLogRepository,
                                ErrorLogRepository errorLogRepository,
                                ActiveSessionRepository activeSessionRepository) {
        this.viewLogRepository = viewLogRepository;
        this.errorLogRepository = errorLogRepository;
        this.activeSessionRepository = activeSessionRepository;
    }

    @Override
    public Map<String, Object> getAnalyticsData() {
        Map<String, Object> data = new HashMap<>();
        data.put("home page views", viewLogRepository.countByPageName("home"));
        data.put("about page views", viewLogRepository.countByPageName("about"));
        data.put("contact page views", viewLogRepository.countByPageName("contact"));
        data.put("errors", errorLogRepository.count());
        data.put("active users", activeSessionRepository.count());
        return data;
    }
}
