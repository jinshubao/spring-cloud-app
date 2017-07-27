package com.jean.environment;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.environment.AbstractScmEnvironmentRepository;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.HashMap;

@ConfigurationProperties("spring.cloud.config.server.db")
public class DatabaseEnvironmentRepository extends AbstractScmEnvironmentRepository {

    private static Logger logger = LoggerFactory.getLogger(DatabaseEnvironmentRepository.class);

    public DatabaseEnvironmentRepository(ConfigurableEnvironment environment) {
        super(environment);
    }

    @Override
    public Environment findOne(String application, String profile, String label) {
        logger.info("----------------------------------------------findOne");
        Environment environment = new Environment(application, new String[]{profile}, label, "", "");
        HashMap<String, Object> configs = new HashMap<>();
        configs.put("com.jean.name","database config name-------------");
        configs.put("com.jean.title","database config title");
        environment.add(new PropertySource(getUri(),configs ));
        return environment;
    }

    @Override
    public Locations getLocations(String application, String profile, String label) {
        logger.info("----------------------------------------------getLocations");
        return new Locations(application, profile, label, "", new String[]{});
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
