package com.freedcamp.utils.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public final class LogManager {

    private static final Logger LOG = LoggerFactory.getLogger(LogManager.class);
    private static final String KEY_TEST = "testName";
    private static final String KEY_ENV = "env";
    private static final String KEY_USER = "user";
    private static final String KEY_CORR = "corrId";

    private LogManager() {}

    public static void startTest(String testName, String env, String user) {
        MDC.put(KEY_TEST, nullSafe(testName));
        MDC.put(KEY_ENV, nullSafe(env));
        MDC.put(KEY_USER, nullSafe(user));
        MDC.put(KEY_CORR, UUID.randomUUID().toString());

        LOG.info("[TEST-START] {}", testName);
    }

    public static void endTestSuccess(Instant startedAt) {
        LOG.info("[TEST-END] success (duration: {})", fmtDuration(startedAt));
        clearContext();
    }

    public static void endTestFailure(Instant startedAt, Throwable t) {
        LOG.error("[TEST-END] FAILED (duration: {})", fmtDuration(startedAt), t);
        clearContext();
    }

    public static void setCorrelationId(String correlationId) {
        MDC.put(KEY_CORR, nullSafe(correlationId));
    }

    public static void clearContext() {
        MDC.remove(KEY_TEST);
        MDC.remove(KEY_ENV);
        MDC.remove(KEY_USER);
        MDC.remove(KEY_CORR);
    }

    // ===== API helpers =====

    public static void apiRequest(String method, String endpoint, Object body) {
        LOG.info("[API-REQUEST] {} {}", method, endpoint);
        if (body != null) {
            LOG.debug("[API-REQUEST-BODY] {}", body);
        }
    }

    public static void apiResponse(int status, long durationMs, Object body) {
        LOG.info("[API-RESPONSE] {} | time={}ms", status, durationMs);
        if (body != null) {
            LOG.debug("[API-RESPONSE-BODY] {}", body);
        }
    }

    public static void apiHeaders(String headersSafeForLog) {
        LOG.debug("[API-HEADERS] {}", headersSafeForLog);
    }

    public static void uiAction(String message) {
        LOG.info("[UI-ACTION] {}", message);
    }

    public static void uiAssert(String message) {
        LOG.info("[UI-ASSERT] {}", message);
    }

    public static void uiLocator(String xpathOrCss) {
        LOG.debug("[UI-LOCATOR] {}", xpathOrCss);
    }

    public static void uiWaitRetry(String what, int attempt, long elapsedMs) {
        LOG.warn("[UI-WAIT] retry#{} for '{}' ({}ms elapsed)", attempt, what, elapsedMs);
    }

    public static void authIssueToken(String roleOrUser) {
        LOG.info("[AUTH] issuing token for {}", roleOrUser);
    }

    public static void authSetThreadLocal() {
        LOG.info("[AUTH] token set in ThreadLocal context");
    }

    public static void authInvalidate(String roleOrUser) {
        LOG.info("[AUTH] token invalidated for {}", roleOrUser);
    }

    private static String fmtDuration(Instant startedAt) {
        if (startedAt == null) return "n/a";
        long ms = Duration.between(startedAt, Instant.now()).toMillis();
        return ms + "ms";
    }

    private static String nullSafe(String v) {
        return Objects.toString(v, "-");
    }
}