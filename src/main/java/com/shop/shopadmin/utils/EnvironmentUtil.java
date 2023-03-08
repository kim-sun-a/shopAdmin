package com.shop.shopadmin.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EnvironmentUtil {
    private final Environment env;

    public Environment getEnv() {
        return env;
    }

    public boolean isLocal() {
        return compareProfiles("local");
    }

    public boolean isDev() {
        return compareProfiles("dev");
    }

    public boolean isProd() {
        return compareProfiles("prod");
    }

    private boolean compareProfiles(String profiles) {
        String envProfile = env.getProperty("spring.profiles");
        if (envProfile == null) {
            envProfile = env.getActiveProfiles()[0];
        }
        return profiles.equals(envProfile);
    }
}
