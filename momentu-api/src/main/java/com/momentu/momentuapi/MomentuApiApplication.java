package com.momentu.momentuapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
@EntityScan(basePackageClasses = { MomentuApiApplication.class })
public class MomentuApiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MomentuApiApplication.class, args);
    }

}
