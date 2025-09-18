package com.website.analytics_system.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "active_sessions")
public class ActiveSession {

    @Id
    private String sessionId;

    private LocalDateTime createdAt;

    private LocalDateTime lastAccessedAt;

    public ActiveSession() {}

    public ActiveSession(String sessionId, LocalDateTime createdAt, LocalDateTime lastAccessedAt) {
        this.sessionId = sessionId;
        this.createdAt = createdAt;
        this.lastAccessedAt = lastAccessedAt;
    }

    // Getters & Setters
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastAccessedAt() {
        return lastAccessedAt;
    }

    public void setLastAccessedAt(LocalDateTime lastAccessedAt) {
        this.lastAccessedAt = lastAccessedAt;
    }
}
