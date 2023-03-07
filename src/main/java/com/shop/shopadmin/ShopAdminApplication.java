package com.shop.shopadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopAdminApplication {

    public static void main(String[] args) {

        String profile = System.getProperty("spring.profiles.active");
        if(profile == null) {
            System.setProperty("spring.profiles.active", "local");
        }
        SpringApplication.run(ShopAdminApplication.class, args);
    }

}
