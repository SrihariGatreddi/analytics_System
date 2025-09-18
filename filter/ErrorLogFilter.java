package com.website.analytics_system.filter;

import com.website.analytics_system.model.ViewLog;
import com.website.analytics_system.model.ErrorLog;
import com.website.analytics_system.repository.ViewLogRepository;
import com.website.analytics_system.repository.ErrorLogRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class ErrorLogFilter implements Filter {

    private final ViewLogRepository viewLogRepository;
    private final ErrorLogRepository errorLogRepository;

    public ErrorLogFilter(ViewLogRepository viewLogRepository,
                          ErrorLogRepository errorLogRepository) {
        this.viewLogRepository = viewLogRepository;
        this.errorLogRepository = errorLogRepository;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // ✅ Force session creation so SessionListener gets triggered
        HttpSession session = req.getSession(true);
        session.setMaxInactiveInterval(60); // 1 minute timeout for demo

        String uri = req.getRequestURI();
        String pageName = getPageNameFromUri(uri);

        // Log page view if it's a tracked page
        if (pageName != null) {
            ViewLog viewLog = new ViewLog(pageName, LocalDateTime.now());
            viewLogRepository.save(viewLog);
        }

        try {
            chain.doFilter(request, response);

            // After processing, check for HTTP errors
            int status = res.getStatus();
            if (status >= 400) {
                String errorMessage = "HTTP Error " + status + " for URI: " + uri;
                ErrorLog errorLog = new ErrorLog(errorMessage, LocalDateTime.now());
                errorLogRepository.save(errorLog);
            }

        } catch (Exception e) {
            // Log server-side exceptions
            ErrorLog errorLog = new ErrorLog(e.getMessage(), LocalDateTime.now());
            errorLogRepository.save(errorLog);
            throw e;
        }
    }

    // Map request URI to a page name
    private String getPageNameFromUri(String uri) {
        if (uri.equals("/") || uri.equals("/index") || uri.equals("/home")) return "home";
        if (uri.equals("/about")) return "about";
        if (uri.equals("/contact")) return "contact";
        return null; // untracked pages
    }
}
