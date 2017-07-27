package com.jean.environment;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.environment.AbstractScmEnvironmentRepository;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.HashMap;

@ConfigurationProperties("spring.cloud.config.server.db")

@ConditionalOnBean(ConfigurableEnvironment.class)
public class DatabaseEnvironmentRepository implements EnvironmentRepository {

    @Autowired
    DatabaseEnvironmentRepository environment;

    @Override
    public Environment findOne(String application, String profile, String label) {
        Environment environment = new Environment(application, new String[]{profile}, label, "", "");
        HashMap<String, Object> configs = new HashMap<>();
        configs.put("com.jean.name", "database config name-------------");
        configs.put("com.jean.title", "database config title");
        environment.add(new PropertySource(environment.getUrl(), configs));
        return environment;
    }
}