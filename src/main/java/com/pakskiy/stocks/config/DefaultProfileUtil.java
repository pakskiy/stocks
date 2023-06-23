package com.pakskiy.stocks.config;

import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class DefaultProfileUtil {
    private static final String SPRING_PROFILE_DEFAULT = "spring.profiles.default";
    public static final String PROD_PROFILE = "master";
    public static final String LOCAL_PROFILE = "local";

    public static void addDefaultProfile(SpringApplication app) {
        Map<String, Object> defProperties = new HashMap<>();
        defProperties.put(SPRING_PROFILE_DEFAULT, LOCAL_PROFILE);
        app.setDefaultProperties(defProperties);
    }

    public static boolean containsProfile(String[] profiles, String profile) {
        return Arrays.binarySearch(profiles, profile) >= 0;
    }

    public static boolean isProdProfileActive(String[] profiles) {
        return containsProfile(profiles, PROD_PROFILE);
    }

}
