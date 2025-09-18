package com.website.analytics_system.listener;

import com.website.analytics_system.model.ActiveSession;
import com.website.analytics_system.repository.ActiveSessionRepository;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SessionCounterListener implements HttpSessionListener {

    private final ActiveSessionRepository activeSessionRepository;

    public SessionCounterListener(ActiveSessionRepository activeSessionRepository) {
        this.activeSessionRepository = activeSessionRepository;
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        String sessionId = se.getSession().getId();
        ActiveSession session = new ActiveSession(sessionId, LocalDateTime.now(), LocalDateTime.now());
        activeSessionRepository.save(session);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        String sessionId = se.getSession().getId();
        activeSessionRepository.findById(sessionId).ifPresent(activeSessionRepository::delete);
    }
}
