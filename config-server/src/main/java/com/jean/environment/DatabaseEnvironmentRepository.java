package com.jean.environment;


import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;

import java.util.HashMap;


public class DatabaseEnvironmentRepository implements EnvironmentRepository {

    @Override
    public Environment findOne(String application, String profile, String label) {
        Environment environment = new Environment(application, new String[]{profile}, label, "", "");
        HashMap<String, Object> configs = new HashMap<>();
        configs.put("com.jean.name", "database config name-------------");
        configs.put("com.jean.title", "database config title");
        environment.add(new PropertySource("aaaaaaaaaaaaaaaaaaaaaaaaaa", configs));
        return environment;
    }
}