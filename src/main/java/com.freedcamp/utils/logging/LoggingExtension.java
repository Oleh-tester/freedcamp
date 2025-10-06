package com.freedcamp.utils.logging;

import org.junit.jupiter.api.extension.*;
import java.time.Instant;

public class LoggingExtension implements BeforeEachCallback, AfterTestExecutionCallback {
    private static final ExtensionContext.Namespace NS = ExtensionContext.Namespace.create(LoggingExtension.class);
    private static final String START_KEY = "startedAt";

    @Override
    public void beforeEach(ExtensionContext ctx) {
        String testName = ctx.getDisplayName();
        String user = System.getProperty("log.user", "qa_auto");

        LogManager.startTest(testName, user);
        ctx.getStore(NS).put(START_KEY, Instant.now());
    }

    @Override
    public void afterTestExecution(ExtensionContext ctx) {
        Instant startedAt = ctx.getStore(NS).remove(START_KEY, Instant.class);
        ctx.getExecutionException()
                .ifPresentOrElse(
                        ex -> LogManager.endTestFailure(startedAt, ex),
                        () -> LogManager.endTestSuccess(startedAt)
                );
    }
}