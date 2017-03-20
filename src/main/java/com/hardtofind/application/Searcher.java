package com.hardtofind.application;

import com.hardtofind.HealthCheck.SearchHealthCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import com.hardtofind.resources.SearchResource;


public class Searcher extends Application<AppConfiguration> {

    public static final Logger LOGGER = LoggerFactory.getLogger(Searcher.class);

    public static void main(final String[] args) throws Exception {
        new Searcher().run(args);
    }

    @Override
    public void run(final AppConfiguration configuration, final Environment environment)
            throws Exception {
        final SearchResource resource = new SearchResource();
        environment.jersey().register(resource);

        final SearchHealthCheck healthCheck =
                new SearchHealthCheck("TEST");
        environment.healthChecks().register("search", healthCheck);
        LOGGER.info("Application name: {}", configuration.getAppName());
    }
}
