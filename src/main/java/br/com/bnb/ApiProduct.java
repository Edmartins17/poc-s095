package br.com.bnb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ApiProduct {

    public static void main(String[] args) {
        SpringApplication.run(ApiProduct.class, args);
    }

}
