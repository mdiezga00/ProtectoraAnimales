package com.hiberus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableEurekaClient
@SpringBootApplication
/*@EnableFeignClients*/
@EnableTransactionManagement
public class ApplicationAdopciones {
    public static void main(String[] args) {

        SpringApplication.run(ApplicationAdopciones.class,args);

    }
}