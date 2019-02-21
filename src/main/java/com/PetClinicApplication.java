package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;

//@ServletComponentScan
//Servlet tanımlarını göstermek için ullanılır
@SpringBootApplication
@EnableConfigurationProperties(value = PetClinicProperties.class)
public class PetClinicApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetClinicApplication.class,args);
    }
}
