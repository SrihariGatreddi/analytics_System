
package com.website.analytics_system.model;

import jakarta.persistence.*;
        import java.time.LocalDateTime;

@Entity
@Table(name = "view_logs")
public class ViewLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pageName;

    private LocalDateTime timestamp;

    public ViewLog() {}

    public ViewLog(String pageName, LocalDateTime timestamp) {
        this.pageName = pageName;
        this.timestamp = timestamp;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
