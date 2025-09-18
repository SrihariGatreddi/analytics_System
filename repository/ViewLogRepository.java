package com.website.analytics_system.repository;

import com.website.analytics_system.model.ViewLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewLogRepository extends JpaRepository<ViewLog, Long> {

    // Count how many times a specific page was viewed
    long countByPageName(String pageName);
}
