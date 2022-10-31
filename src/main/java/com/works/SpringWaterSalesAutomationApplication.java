package com.works;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

@Configuration
@Component
@SpringBootApplication
@EnableJpaAuditing
@EnableCaching
public class SpringWaterSalesAutomationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWaterSalesAutomationApplication.class, args);
    }

}
