package com.it_cgm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@ComponentScan(basePackages = {"com.cgm"})
@PropertySources({
        @PropertySource({"classpath:/application-base.properties"}),
        @PropertySource(ignoreResourceNotFound = true, value = {"classpath:/application.properties"})
})
public class CGMApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(CGMApplication.class);

    public static void main(String[] args) {
        LOGGER.debug("Initializing SpringBoot Application..");
        new SpringApplicationBuilder().sources(CGMApplication.class)
                .registerShutdownHook(true)
                .run(args);
    }
}