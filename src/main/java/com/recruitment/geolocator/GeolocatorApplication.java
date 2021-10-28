package com.recruitment.geolocator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class GeolocatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeolocatorApplication.class, args);
    }

}
