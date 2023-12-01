package com.gyunf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author gyf
 * @date 2023-11-13 14:32
 */
@EnableScheduling
@SpringBootApplication
class TestApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(TestApplication.class);
        springApplication.run(args);
    }

}
