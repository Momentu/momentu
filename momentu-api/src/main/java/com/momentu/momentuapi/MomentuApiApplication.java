package com.momentu.momentuapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// Temporarily Ignore Database
@Configuration
@ComponentScan
public class MomentuApiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MomentuApiApplication.class, args);
    }

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MomentuApiApplication.class);
    }

}
