package com.jean.environment;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.server.environment.AbstractScmEnvironmentRepository;
import org.springframework.core.env.ConfigurableEnvironment;

@ConfigurationProperties("spring.cloud.config.server.db")
public class DatabaseEnvironmentRepository extends AbstractScmEnvironmentRepository {

    private static Logger logger = LoggerFactory.getLogger(DatabaseEnvironmentRepository.class);

    public DatabaseEnvironmentRepository(ConfigurableEnvironment environment) {
        super(environment);
    }

    @Override
    public Environment findOne(String application, String profile, String label) {
        logger.info("findOne");
        return null;
    }

    @Override
    public Locations getLocations(String application, String profile, String label) {
        logger.info("getLocations");
        return null;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
