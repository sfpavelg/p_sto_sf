package com.javamentor.qa.platform.webapp.configs;

import com.javamentor.qa.platform.webapp.configs.SpringFoxConfig.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan("com.javamentor.qa.platform")
@EntityScan("com.javamentor.qa.platform.models.entity")
@ComponentScan(basePackageClasses = {JmApplication.class, SwaggerConfig.class})
@EnableSwagger2
public class JmApplication {

    public static void main(String[] args) {
        SpringApplication.run(JmApplication.class, args);
    }

}

