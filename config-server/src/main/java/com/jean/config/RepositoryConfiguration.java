package com.jean.config;

import com.jean.environment.DatabaseEnvironmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.ConfigurableEnvironment;

@Configuration
public class RepositoryConfiguration {

    @Configuration
    @Profile("database")
    protected static class DatabaseConfiguration {

        @Autowired
        private ConfigurableEnvironment environment;

        @Bean
        public DatabaseEnvironmentRepository databaseEnvironmentRepository() {
            return new DatabaseEnvironmentRepository(this.environment);
        }
    }
}
