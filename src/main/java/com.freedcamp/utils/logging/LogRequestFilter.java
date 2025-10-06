package com.freedcamp.utils.logging;

import io.restassured.filter.FilterContext;
import io.restassured.filter.OrderedFilter;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.List;

public class LogRequestFilter implements OrderedFilter {
    private static final Logger logger = LoggerFactory.getLogger(LogRequestFilter.class);
    private static final List<String> skipEndpoints = List.of("/authenticate");
    private static final int MAX_BODY_LENGTH = 25000;

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx) {

        String testName = MDC.get("testName");

        String retryCount = requestSpec.getHeaders().getValue("X-Retry-Count");

        logger.info("[{}][Retry #{}] {} {}",
                testName != null ? testName : "unknown",
                retryCount != null ? retryCount : "0",
                requestSpec.getMethod(),
                requestSpec.getURI());
        requestSpec.removeHeader("X-Retry-Count");

        if (skipEndpoints.stream().anyMatch(requestSpec.getURI()::contains)) {
            return ctx.next(requestSpec, responseSpec);
        }


        if (requestSpec.getBody() != null) {
            logger.info("[{}] Request body:\n{}", testName, requestSpec.getBody());
        }

        Response response = ctx.next(requestSpec, responseSpec);

        logger.info("[{}][Retry #{}] {} {}",
                testName != null ? testName : "unknown",
                retryCount != null ? retryCount : "0",
                response.getStatusCode(),
                requestSpec.getURI());

        if (needToLogResponse()) {
            String responseBody = response.asString();
            if (responseBody.length() > MAX_BODY_LENGTH) {
                logger.info("Content-Type: {}\n[RESPONSE TOO LONG: {} chars, skipped]",
                        response.getHeader("Content-Type"), responseBody.length());
            } else {
                logger.info("[{}] Content-Type: {}\n{}",
                        testName,
                        response.getHeader("Content-Type"),
                        response.asPrettyString());
            }
        }
        return response;
    }

    private boolean needToLogResponse() {
        return true;
    }

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }
}
