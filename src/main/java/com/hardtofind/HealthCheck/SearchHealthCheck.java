package com.hardtofind.HealthCheck;

import com.codahale.metrics.health.HealthCheck;

/**
 * Created by zdrillings on 3/19/17.
 */
public class SearchHealthCheck extends HealthCheck {

    private final String result;

    public SearchHealthCheck(String result) {
        this.result = result;
    }

    @Override
    protected Result check() throws Exception {
        final String res = String.format(result, "TEST");
        if (!res.contains("TEST")) {
            return Result.unhealthy("template doesn't include a name");
        }
        return HealthCheck.Result.healthy();
    }
}
