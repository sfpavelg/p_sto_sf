package com.javamentor.qa.platform.webapp.configs;

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

// Конфигурация миграций Flyway
@Configuration
public class FlywayStrategy{
//    @Bean
//    public FlywayMigrationStrategy onlyMigrateStrategy(){
//        FlywayMigrationStrategy strategy = new FlywayMigrationStrategy() {
//            @Override
//            public void migrate(Flyway flyway) {
//                flyway.migrate();
//            }
//        };
//        return strategy;
//    }

//    @Bean
//    public FlywayMigrationStrategy cleanMigrateStrategy() {
//        FlywayMigrationStrategy strategy = new FlywayMigrationStrategy() {
//            @Override
//            public void migrate(Flyway flyway) {
//                flyway.clean();
//            }
//        };
//        return strategy;
//    }

    @Bean
    public FlywayMigrationStrategy cleanBeforeMigrateMigrateStrategy() {
        FlywayMigrationStrategy strategy = new FlywayMigrationStrategy() {
            @Override
            public void migrate(Flyway flyway) {
                flyway.clean();
                flyway.migrate();
            }
        };
        return strategy;
    }
}
