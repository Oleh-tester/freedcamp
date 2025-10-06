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
    private static final String KEY_USER = "user";
    private static final String KEY_CORR = "corrId";

    private LogManager() {}

    public static void startTest(String testName, String user) {
        MDC.put(KEY_TEST, nullSafe(testName));
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

    public static void clearContext() {
        MDC.remove(KEY_TEST);
        MDC.remove(KEY_USER);
        MDC.remove(KEY_CORR);
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